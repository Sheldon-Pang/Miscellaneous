/**
 * Simulate a cloud-like server instance host.
 *
 * @author Sheldon Pang
 * @version 1.0
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "InstanceHost.h"

/**
* Initializes the host environment.
*/
host* host_create() {
    host* host = (struct host*)malloc(sizeof(host));
    return host;
}

/**
* Shuts down the host environment. Ensures any outstanding batches have
* completed.
*/
void host_destroy(host** host) {
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
    while(batch != NULL) {
        *batch->data_result = batch->data * batch->data;
        batch = batch->next;
    }
    h->number_of_instances--; /* finished and decrement number of instances */
}