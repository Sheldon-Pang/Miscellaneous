/**
 * Implementation of Class to simulate a page table.
 *
 * @author Sheldon Pang
 * @version 1.0
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "PageTable.h"

struct page_table_entry {
    int frame_number;
    int valid_bit;
    int dirty_bit;
    int counter; /* for MFU algorithm */
    int timer;   /* for LRU algorithm */
};

struct page_table {
    struct page_table_entry entry[512];
    int page_count;
    int frame_count;
    enum replacement_algorithm algorithm;
    int page_faults;
    int verbose;
};

/* Function Forward Declaration */
int* init_frames(struct page_table *pt);
void FIFO_algorithm(struct page_table *pt, int page);
void LRU_algorithm(struct page_table *pt, int page);

/* Global Variable for circular array implementation */
int* frames;
int head = -1, tail = -1;

/**
 * Creates a new page table object. Returns a pointer to created page table.
 *
 * @param page_count Number of pages.
 * @param frame_count Numbers of frames.
 * @param algorithm Page replacement algorithm
 * @param verbose Enables showing verbose table contents.
 * @return A page table object.
 */
struct page_table* page_table_create(int page_count,int frame_count,enum replacement_algorithm algorithm,int verbose){
    /* allocate memory base on number of page_count */
    struct page_table* data =
            (struct page_table*) malloc(sizeof (struct page_table) * page_count);

    data->page_count = page_count;
    data->frame_count = frame_count;
    data->algorithm = algorithm;
    data->page_faults = 0;
    data->verbose = verbose;
    /* each page will contain a page_table_entry */
    for (int i = 0; i < page_count; i++) {
        data->entry[i].frame_number = 0;
        data->entry[i].valid_bit = 0;
        data->entry[i].dirty_bit = 0;
        data->entry[i].counter = 0;
        data->entry[i].timer = 0;
    }

    frames = init_frames(data);

    return data;
}

/**
 * destroys an existing page table object. Sets outside variable to NULL.
 *
 * @param pt A page table object.
 */
void page_table_destroy(struct page_table** pt) {
    free(*pt);
    *pt = NULL;
}

/**
 * Simulates an instruction accessing a particular page in the page table.
 *
 * @param pt A page table object.
 * @param page The page being accessed.
 */
void page_table_access_page(struct page_table *pt, int page) {
    if (pt->algorithm == 0) {
        FIFO_algorithm(pt, page);
    }
    if (pt->algorithm == 1) {
        LRU_algorithm(pt, page);
    }
    if (pt->algorithm == 2) {
        MFU_algorithm(pt, page);
    }
}

/**
 * Initialize frames base on frame_count
 *
 * @param pt A page table object.
 */
int* init_frames(struct page_table *pt) {
    int frame_count = pt->frame_count;

    frames = malloc(sizeof (int) * frame_count);

    for (int i = 0; i < frame_count; i++) {
        frames[i] = -1; /* initialize, -1 meaning the frames is empty */
    }

    return frames;
}

/**
 * Reset frames
 *
 */
 void reset_frames(struct page_table *pt) {
    int frame_count = pt->frame_count;
     for (int i = 0; i < frame_count; i++) {
        frames[i] = -1; /* initialize, -1 meaning the frames is empty */
    }

 }

/**
 * Free frames
 *
 */
void destroy_frames() {
    free(frames);
    frames = NULL;
}

/**
 * FIFO page replacement algorithm
 * Circular queue implementation using array
 *
 * @param pt A page table object.
 * @param page The page being accessed.
 */
void FIFO_algorithm(struct page_table *pt, int page) {
     int queue_size = pt->frame_count;

    if ((head == tail + 1) || (head == 0 && tail == queue_size - 1)) { /* Frame is full */
        for (int i = 0; i < queue_size; i++) { /* Check if page already in frame */
            if (frames[i] == page)
                return;
        }
        /* Replace head, and update head and tail */
        int temp_page = frames[head];
        frames[head] = page;
        /* Update page table entry */
        pt->entry[page].valid_bit = 1;
        pt->entry[page].frame_number = head;
        pt->entry[temp_page].valid_bit = 0;
        pt->page_faults++;

        head = (head + 1) % queue_size;
        tail = (tail + 1) % queue_size;
    } else { /* empty, add page to frame */

        for (int i = 0; i < queue_size; i++) { /* Check if page already in frame */
            if (frames[i] == page)
                return;
        }

        if (head == -1)
            head = 0;
        tail = (tail + 1) % queue_size;
        frames[tail] = page;
        /* Update page table entry */
        pt->entry[page].valid_bit = 1;
        pt->entry[page].frame_number = tail;
        pt->page_faults++;
    }
}

/**
 * LRU page replacement algorithm
 *
 * @param pt A page table object.
 * @param page The page being accessed.
 */
void LRU_algorithm(struct page_table *pt, int page) {
    int frame_count = pt->frame_count;

    for (int i = 0; i < frame_count; i++) {
        if (frames[i] != -1) { /* increment timer for all valid page */
            int temp_page = frames[i];
            pt->entry[temp_page].timer++;
        }
        if (frames[i] == page) { /* Page already in frame */
            if (i < frame_count - 1) {
                for (int j = i + 1; j < frame_count; j++) { /* before return, increment timer for all valid page left */
                    if (frames[j] != -1) {
                        int temp_page = frames[j];
                        pt->entry[temp_page].timer++;
                    }
                }
            }
            pt->entry[page].timer = 0; /* reset timer for the page */
            return;
        }
        if (frames[i] == -1) { /* Fill empty frames */
            frames[i] = page;
            pt->entry[page].valid_bit = 1;
            pt->entry[page].frame_number = i;
            pt->page_faults++;
            pt->entry[page].timer++;
            return;
        }
    }
    /* page fault occurred */
    int max_count = 0;
    int frame_number_has_largest_timer = -1;

    for (int i = 0; i < frame_count; i++) { /* Find frame that has the largest timer */
        int temp_page = frames[i];
        if (max_count < pt->entry[temp_page].timer) {
            max_count = pt->entry[temp_page].timer;
            frame_number_has_largest_timer = i;
        }
    }

    int temp_page = frames[frame_number_has_largest_timer];
    pt->entry[temp_page].valid_bit = 0;
    frames[frame_number_has_largest_timer] = page;
    pt->entry[temp_page].timer = 0; /* reset timer for page that swapped out */

    pt->entry[page].valid_bit = 1;
    pt->entry[page].frame_number = frame_number_has_largest_timer;
    pt->page_faults++;
    pt->entry[page].timer++;
}

/**
 * MFU page replacement algorithm
 *
 * @param pt A page table object.
 * @param page The page being accessed.
 */
void MFU_algorithm(struct page_table *pt, int page) {
    int frame_count = pt->frame_count;

    for (int i = 0; i < frame_count; i++) {
        if (frames[i] != -1) { /* increment counter for all valid page */
            int temp_page = frames[i];
            pt->entry[temp_page].counter++;
        }
        if (frames[i] == page) { /* Page already in frame */
            if (i < frame_count - 1) {
                for (int j = i + 1; j < frame_count; j++) { /* before return, increment all valid page left */
                    if (frames[j] != -1) {
                        int temp_page = frames[j];
                        pt->entry[temp_page].counter++;
                    }
                }
            }
            return;
        }
        if (frames[i] == -1) { /* Fill empty frames */
            frames[i] = page;
            pt->entry[page].valid_bit = 1;
            pt->entry[page].frame_number = i;
            pt->page_faults++;
            pt->entry[page].counter++;
            return;
        }
    }
    /* page fault occurred */
    int max_count = 0;
    int frame_number_has_max_count = -1;

    for (int i = 0; i < frame_count; i++) { /* Find frame that has the largest count */
        int temp_page = frames[i];
        if (max_count < pt->entry[temp_page].counter) {
            max_count = pt->entry[temp_page].counter;
            frame_number_has_max_count = i;
        }
    }

    int temp_page = frames[frame_number_has_max_count];
    pt->entry[temp_page].valid_bit = 0;
    frames[frame_number_has_max_count] = page;

    pt->entry[page].valid_bit = 1;
    pt->entry[page].frame_number = frame_number_has_max_count;
    pt->page_faults++;
    pt->entry[page].counter++;
}

/**
 * Displays page table replacement algorithm, number of page faults, and the
 * current contents of the page table.
 *
 * @param pt A page table object.
 */
void page_table_display(struct page_table* pt) {
    printf("\n==== Page Table ====\n");
    if (pt->algorithm == 0) {
        printf("Mode: FIFO\n");
    } else if (pt->algorithm == 1) {
        printf("Mode: LRU\n");
    } else if (pt->algorithm == 2) {
        printf("Mode: MFU\n");
    }
    printf("Page Faults: %d\n", pt->page_faults);
    printf("page frame | dirty valid\n");
    for (int i = 0; i < pt->page_count; i++) {
        printf("   %d     %d |     %d     %d\n",
               i,
               pt->entry[i].frame_number,
               pt->entry[i].dirty_bit,
               pt->entry[i].valid_bit
               );
    }
}

/**
 * Displays the current contents of the page table.
 *
 * @param pt A page table object.
 */
void page_table_display_contents(struct page_table *pt) {
    printf("\n==== Page Table ====\n");
    printf("page frame | dirty valid\n");
    for (int i = 0; i < pt->page_count; i++) {
        printf("   %d     %d |     %d     %d\n",
               i,
               pt->entry[i].frame_number,
               pt->entry[i].dirty_bit,
               pt->entry[i].valid_bit
        );
    }
}