/**
 * Simulate a cloud-like server instance host.
 *
 * @author Sheldon Pang
 * @version 1.0
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "InstanceHost.h"

struct host {
    pthread_t* th;
};

pthread_mutex_t list_lock; /* mutex lock */
pthread_t th[100];
int instance_id = 0;

void* runner_computation(void* nodeHead);

/**
* Initializes the host environment.
*/
host* host_create() {
    host* host = (struct host*)malloc(sizeof(struct host));
    return host;
}

/**
* Shuts down the host environment. Ensures any outstanding batches have
* completed.
*/
void host_destroy(host** host) {
    /* any outstanding batches is handled in balancer_destroy */

    free(*host);
    *host = NULL;
}

/**
* Creates a new server instance (i.e., thread) to handle processing the items
* contained in a batch (i.e., a listed list of job_node). InstanceHost will
* maintain a list of active instances, and if the host is requested to
* shutdown, ensures that all jobs are completed.
*
* @param job_batch_list A list containing the jobs in a batch to process.
*/
void host_request_instance(host* h, struct job_node* batch) {
    printf("LoadBalancer: Received batch and spinning up new instance.\n");

    if (pthread_create(&th[instance_id], NULL, &runner_computation, (void*)batch) != 0)
        perror("Failed to create thread");
    if (pthread_join(th[instance_id], NULL) != 0)
        perror("Failed to join thread");

    instance_id++;
}

/**
* Pthread runner function used to calculate the squares of a number
*/
void* runner_computation(void* nodeHead) {
    pthread_mutex_init(&list_lock, NULL);       /* initialize mutex to prevent race condition */
    pthread_mutex_lock(&list_lock);             /* Critical Section */

    while(nodeHead != NULL) {
        /* cast void type to struct job_node* and loop nodes and compute squares */
        *((struct job_node*)nodeHead)->data_result = ((struct job_node*)nodeHead)->data * ((struct job_node*)nodeHead)->data;
        nodeHead = ((struct job_node*)nodeHead)->next;
    }

    pthread_mutex_unlock(&list_lock);           /* Critical Section */
    pthread_mutex_destroy(&list_lock);          /* mutex destroy */

    pthread_exit(0);
}