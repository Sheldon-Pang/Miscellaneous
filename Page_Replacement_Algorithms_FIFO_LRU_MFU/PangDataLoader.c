/**
 * Implementation of the helper functions to load a reference string.
 *
 * @author Sheldon Pang
 * @version 1.0
 */

#include <stdio.h>
#include <stdlib.h>
#include "PangDataLoader.h"

#define MAX_LENGTH 512

/* Structure test_scenario */
/**
 * int refstr_len;
 * int refstr[512];
 * int page_count;
 * int frame_count;
 */

/**
 * Loads a test_scenario strut from a textfile.
 *
 * @param filename The name of the file to load.
 * @return A struct containing the loaded file.
 *
 * Note: For the input data file
 *  First line gives the number of pages for the system
 *  Second line gives the number of frames
 *  Third line lists the number of page requests in the reference string
 */
struct test_scenario* load_test_data(char* filename) {
    struct test_scenario* data =
            (struct test_scenario*) malloc(sizeof (struct test_scenario));

    FILE *file = fopen(filename, "r");

    if(!file) {
        perror("Error: File Open\n");
        exit(1);
    }

    char line[512];

    fgets(line, MAX_LENGTH, file);
    data->page_count = atoi(line);
    printf("Number of page: %d\n", data->page_count);

    fgets(line, MAX_LENGTH, file);
    data->frame_count = atoi(line);
    printf("Number of frame: %d\n", data->frame_count);

    fgets(line, MAX_LENGTH, file);
    data->refstr_len = atoi(line);
    printf("Number of page request: %d\n", data->refstr_len);

    printf("Reference String: ");
    for (int i = 0; i < data->refstr_len; i++) {
        fgets(line, MAX_LENGTH, file);
        data->refstr[i] = atoi(line);
        printf("%d, ", data->refstr[i]);
    }
    printf("\n");

    fclose(file);
    return data;
}