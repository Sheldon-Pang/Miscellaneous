/**
 * PangLKM.c
 *
 * A Linux kernel module that displays processes whose PID is greater
 * than an integer given by the user as a module parameter.
 * 
 * To compile, run makefile by entering "make"
 *
 * Author: Bichen Pang
 */

#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/sched.h>
#include <linux/sched/signal.h>
#include <linux/list.h>

/*
 * Set up module parameter that takes an int from user representing process PID
 * */
static int inp_pid = 0;
module_param(inp_pid, int, S_IRUGO);
MODULE_PARM_DESC(inp_pid, "Process PID\n");


/*
 * This function is called when the module is loaded.
 * */
int PangLKM_init(void)
{
    struct task_struct *currentProcess, *child_ptr;
    struct list_head *child_head;

    printk(KERN_INFO "Loading Module PangLKM\n");
    printk(KERN_INFO "\n--------------------------------All running Processes with PID > %d--------------------------------\n", inp_pid);

    // iterate through each currently running processes
    for_each_process(currentProcess) {
        if (currentProcess->pid > inp_pid) { // display PID greater than user input

            printk(KERN_INFO "%20s %10s %10s %12s %15s %15s\n",
                   "PROCESS NAME","PID","STATE","PRIORITY","STATIC_PRIO.", "NORMAL_PRIO.");
            printk(KERN_INFO "%20s %10d %10d %10d %12d %15d\n",
                   currentProcess->comm,
                   currentProcess->pid,
                   currentProcess->__state,
                   currentProcess->prio,
                   currentProcess->static_prio,
                   currentProcess->normal_prio);

            // check if the current process has any children
            if (!list_empty(&currentProcess->children)) {
                printk(KERN_INFO "%20s\n", "CHILD");

                // fetch child processes of the current process
                list_for_each(child_head, &currentProcess->children){

                    child_ptr = list_entry(child_head, struct task_struct, sibling);
                    printk(KERN_INFO "%20s %10d %10d %10d %12d %15d\n",
                            child_ptr->comm,
                            child_ptr->pid,
                            child_ptr->__state,
                            child_ptr->prio,
                            child_ptr->static_prio,
                            child_ptr->normal_prio);
                }
            }

            // fetch parent processes of the current process
            printk(KERN_INFO "%20s\n","PARENT");
            printk(KERN_INFO "%20s %10d %10d %10d %12d %15d\n",
                    currentProcess->parent->comm,
                    currentProcess->parent->pid,
                    currentProcess->parent->__state,
                    currentProcess->parent->prio,
                    currentProcess->parent->static_prio,
                    currentProcess->parent->normal_prio);


            printk(KERN_INFO "\n"); //Print separator between each process
        }
    }


    return 0;
}

/*
 * This function is called when the module is removed.
 * */
void PangLKM_exit(void) {
	printk(KERN_INFO "Removing Module PangLKM\n");
}

/* Macros for registering module entry and exit points. */
module_init( PangLKM_init );
module_exit( PangLKM_exit );

MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("Show processes PID greater than user input parameter");
MODULE_AUTHOR("Sheldon Pang");

