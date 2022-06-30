/**
 * Program to simulate Shortest-Job-First/Live Scheduling Algorithm.
 * SJF-Live algorithm is able to predicted how long a process would
 * take given it's previous history of execution.
 *
 * Some constraints:
 *  - Number of processes should be less than 50
 *  - Number of simulation should be less than 500
 *
 * @author Sheldon Pang
 * @version 1.0
 */

#include <stdio.h>
#include <stdlib.h>
#include <float.h>
#include <math.h>

#define MAX_LENGTH 512

/* Structure */
struct process_data {
    int process_id;
    double tau_value;
    double alpha_value;
    double burst_time[MAX_LENGTH];
    double estimated_burst_time[MAX_LENGTH];
};

/* Function Forward Declaration */
struct process_data** load_input_file_data(char* filename);
void shortest_job_first(struct process_data **pt);
void shortest_job_first_live(struct process_data **pt);
void process_destroy(struct process_data** pt);

/* Global Variable */
int number_of_simulation = 0;
int number_of_process = 0;

int main() {
    char* filename = "data.txt";

    struct process_data** process_data = load_input_file_data(filename);

    /* Simulate shortest_job_first algorithm */
    shortest_job_first(process_data);

    /* Simulate shortest_job_first_live algorithm */
    shortest_job_first_live(process_data);

    process_destroy(process_data);

    return 0;
}

/**
 * Loads a input_data strut from a text file.
 *
 * @param filename The name of the file to load.
 * @return A struct containing the loaded file.
 *
 * Note: For the input data file
 *  First line lists the number of simulation "ticks"
 *  Second line lists number of processes
 *  Each process number is followed by
 *  a tau value, a alpha value, and data for actual run times
 */
struct process_data** load_input_file_data(char* filename) {

    FILE *file = fopen(filename, "r");

    if(!file) {
        perror("Error: File Open\n");
        exit(1);
    }

    char line[512];

    fgets(line, MAX_LENGTH, file);
    number_of_simulation = atoi(line);
    printf("Number of simulation ""ticks"": %d\n", number_of_simulation);

    fgets(line, MAX_LENGTH, file);
    number_of_process = atoi(line);
    printf("Number of processes: %d\n", number_of_process);

    /* Allocate memory base on number_of_process to store processes data */
    struct process_data* process_data[number_of_process];

    for (int i = 0; i < number_of_process; i++) {

        process_data[i] = (struct process_data*) malloc(sizeof (struct process_data));

        fgets(line, MAX_LENGTH, file);
        process_data[i]->process_id = atoi(line);
        printf("process id: %d | ", process_data[i]->process_id);

        fgets(line, MAX_LENGTH, file);
        process_data[i]->tau_value = atof(line);
        printf("tau: %.1f | ", process_data[i]->tau_value);

        fgets(line, MAX_LENGTH, file);
        process_data[i]->alpha_value = atof(line);
        printf("alpha: %.1f\n", process_data[i]->alpha_value);

        printf(" -Burst time: ");
        for (int j = 0; j < number_of_simulation; j++) {
            fgets(line, MAX_LENGTH, file);
            process_data[i]->burst_time[j] = atof(line);
            printf("%.1f, ", process_data[i]->burst_time[j]);
        }
        printf("\n");
    }

    fclose(file);
    return process_data;
}

/**
 * Simulate shortest_job_first algorithm.
 * The result will output Turnaround time and Waiting time
 *
 * @param pt pt process object
 */
void shortest_job_first(struct process_data **pt) { /* V.1 Time: O(n^2), Space: O(n) */

    double waiting_time = 0;
    double turnaround_time = 0;
    double total_running_time = 0;
    double temp_burst_time[50], sorted_burst_time[100];
    double temp_smallest_burst_time = DBL_MAX;
    int temp_process_id = -1;

    printf("\n== Shortest - Job - First ==\n");
    for (int i = 0; i < number_of_simulation; i++) {
        printf("Simulating %dth tick of processes @ time %.1f:\n",
               i, total_running_time);
        /* Store all processes burst time at tick i into an array */
        for (int j = 0; j < number_of_process; j++) {
            temp_burst_time[j] = pt[j]->burst_time[i];
        }
        /* Sort the temp_burst_time[] array in ascending order */
        for (int n = 0; n < number_of_process; n++) {
            /* Find smallest Burst Time */
            for (int m = 0; m < number_of_process; m++) {
                if ((temp_smallest_burst_time > temp_burst_time[m]) &&
                        (temp_burst_time[m] != -1)) {
                    temp_smallest_burst_time = temp_burst_time[m];
                    temp_process_id = m;
                }
            }
            /* The smallest found is stored at sorted_burst_time[] in asc. order */
            sorted_burst_time[n] = temp_smallest_burst_time;
            /* Process id is stored at index[number_of_process + n] */
            sorted_burst_time[number_of_process + n] = temp_process_id;
            temp_burst_time[temp_process_id] = -1;
            temp_smallest_burst_time = DBL_MAX;
        }
        /* Sorted, Ready for execution */
        double temp_burst_time_sum = 0;
        for (int k = 0; k < number_of_process; k++) {
            printf("Process %.0f took %.1f.\n",
                   sorted_burst_time[number_of_process + k], sorted_burst_time[k]);
            total_running_time += sorted_burst_time[k];
            temp_burst_time_sum += sorted_burst_time[k];
            turnaround_time +=  temp_burst_time_sum;
            if (k > 0)
                waiting_time += sorted_burst_time[k - 1];
        }
    }

    printf("------------------------\n");
    printf(" -Turnaround time : %.1f\n", turnaround_time);
    printf(" -Waiting time : %.1f\n", waiting_time);
}

/**
 * Simulate shortest_job_first_live algorithm.
 * The result will output Turnaround time and Waiting time
 *
 * @param pt pt process object
 */
void shortest_job_first_live(struct process_data **pt) {

    double waiting_time = 0;
    double turnaround_time = 0;
    double total_running_time = 0;
    double temp_estimated_burst_time[50], sorted_estimated_burst_time[200];
    double temp_smallest_burst_time = DBL_MAX;
    int temp_process_id = -1;
    double estimation_error = 0;

    printf("\n== Shortest - Job - First Live ==\n");
    for (int tick = 0; tick < number_of_simulation; tick++) {
        printf("Simulating %dth tick of processes @ time %.1f:\n",
               tick, total_running_time);

        if (tick == 0) { /* First tick is based on tau only */
            for (int p = 0; p < number_of_process; p++) {
                pt[p]->estimated_burst_time[tick] = pt[p]->tau_value;
            }
        } else { /* Calculate process estimated burst time, update tau */
            for (int p = 0; p < number_of_process; p++) {
                pt[p]->estimated_burst_time[tick] =
                        (pt[p]->alpha_value * pt[p]->burst_time[tick - 1]) +
                                ((1 - pt[p]->alpha_value) * pt[p]->tau_value);
                pt[p]->tau_value =  pt[p]->estimated_burst_time[tick];
            }
        }

        /* Store all processes burst time at tick into an array */
        for (int j = 0; j < number_of_process; j++) {
            temp_estimated_burst_time[j] = pt[j]->estimated_burst_time[tick];
        }

        /* Sort the temp_estimated_burst_time[] in ascending order */
        for (int n = 0; n < number_of_process; n++) {

            for (int m = 0; m < number_of_process; m++) { /* Find smallest Burst Time */
                if ((temp_smallest_burst_time > temp_estimated_burst_time[m]) &&
                    (temp_estimated_burst_time[m] != -1)) {
                    temp_smallest_burst_time = temp_estimated_burst_time[m];
                    temp_process_id = m;
                }
            }

            /* The smallest found is stored at sorted_estimated_burst_time[] in asc. order */
            sorted_estimated_burst_time[n] = temp_smallest_burst_time;
            /* Process id is stored at index[number_of_process + n] */
            sorted_estimated_burst_time[number_of_process + n] = temp_process_id;
            /* Actual burst time is store at index[number_of_process * 2 + n] */
            sorted_estimated_burst_time[number_of_process * 2 + n] = pt[temp_process_id]->burst_time[tick];
            temp_estimated_burst_time[temp_process_id] = -1;
            temp_smallest_burst_time = DBL_MAX;
        }

        /* Sorted, Ready for execution */
        double temp_burst_time_sum = 0;

        for (int k = 0; k < number_of_process; k++) {

            printf("Process %.0f was estimated for %.1f and took %.1f.\n",
                   sorted_estimated_burst_time[number_of_process + k],
                   sorted_estimated_burst_time[k] ,
                   sorted_estimated_burst_time[number_of_process * 2 + k]);

            total_running_time += sorted_estimated_burst_time[number_of_process * 2 + k];
            temp_burst_time_sum += sorted_estimated_burst_time[number_of_process * 2 + k];
            turnaround_time +=  temp_burst_time_sum;
            estimation_error += fabs(sorted_estimated_burst_time[k] - sorted_estimated_burst_time[number_of_process * 2 + k]);
            if (k > 0)
                waiting_time += sorted_estimated_burst_time[number_of_process * 2 + k - 1];
        }
    }

    printf("------------------------\n");
    printf(" -Turnaround time : %.1f\n", turnaround_time);
    printf(" -Waiting time : %.1f\n", waiting_time);
    printf(" -Estimation Error : %.1f\n", estimation_error);
}

/**
 * Free memory
 *
 * @param pt pt process object
 */
void process_destroy(struct process_data** pt) {
    for (int i = 0; i < number_of_process; i++) {
        free(pt[i]);
        pt[i] = NULL;
    }
}



