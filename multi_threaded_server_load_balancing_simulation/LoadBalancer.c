/**
 * Simulate a load balancer.
 *
 * @author Sheldon Pang
 * @version 1.0
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "LoadBalancer.h"
#include "InstanceHost.h"

struct job_node* list_head = NULL;
pthread_mutex_t list_lock;  /* mutex lock */

/* global variable for batch size and number of request */
int batch_size_global = 0;
int number_of_requests_global = 0;

/* initialize Instance Host */
host* h;

/**
 * Initializes the load balancer. Takes batch size as parameter.
 */
balancer* balancer_create(int batch_size) {
    balancer* bl = (balancer*)malloc(sizeof(int));
    batch_size_global = batch_size;

    /* initialize Instance Host */
    h = host_create();

    return bl;
}

/**
 * Shuts down the load balancer. Ensures any outstanding batches have
 * completed.
 */
void balancer_destroy(balancer** lb) {
    /* process any outstanding batches */
    printf("-------------Processing any outstanding batches-------------\n");
    host_request_instance(h, list_head);

    /* Once finished destroy the Instance Host */
    host_destroy(&h);
    nanosleep((struct timespec[]){{1, 0}}, NULL); /* wait one seconds */
    printf("-------------All instances finished and host is destroyed-------------\n");

    /* destroy Balancer */
    free(*lb);
    *lb = NULL;
}

/**
 * Adds a job to the load balancer. If enough jobs have been added to fill a
 * batch, will request a new instance from InstanceHost. When job is complete,
 * *data_return will be updated with the result.
 *
 * @param user_id the id of the user making the request.
 * @param data the data the user wants to process.
 * @param data_return a pointer to a location to store the result of processing.
 */
void balancer_add_job(balancer* lb, int user_id, int data, int* data_return) {
    printf("LoadBalancer: Received new job from user #%d to process data = %d"
           " and store it at %p.\n", user_id, data, data_return);

    pthread_mutex_init(&list_lock, NULL);       /* initialize mutex to prevent race condition */
    pthread_mutex_lock(&list_lock);             /* Critical Section */

    struct job_node* jobNode = (struct job_node*)malloc(sizeof(struct job_node));
    jobNode->user_id = user_id;
    jobNode->data = data;
    jobNode->data_result = data_return;

    /* add new job to the front of the list */
    jobNode->next = list_head;
    list_head = jobNode;

    number_of_requests_global++; /* increment number_of_requests when a new job is added */

    if (number_of_requests_global == batch_size_global) { /* execute when requests reach batch_size set by user */

        /* delete the batch from the list */
        struct job_node* tempNode; /* a temp node to store the pointer to list head */
        tempNode = list_head;      /* use tempNode pointer in host_request_instance */
        int i = batch_size_global;
        while (i != 0) { /* loop and delete front node based on batch_size */
            list_head = list_head->next;
            i--;
        }
        number_of_requests_global = number_of_requests_global - batch_size_global;

        /* call host_request_instance */
        host_request_instance(h, tempNode);
    }

    pthread_mutex_unlock(&list_lock);           /* Critical Section */
    pthread_mutex_destroy(&list_lock);          /* mutex destroy */
}
