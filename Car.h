#ifndef CAR_H
#define CAR_H

//defining string of char as string
typedef char* string;

/**
 * Making struct Car with 3 members.
 * brand, model and date of manufacture.
 *
 * Assume valid brand, make and date of manufacture 
 * when making a struct Car
 */
struct Car {
    string brand;
    string model;
    string date;
    
};
//defining struct Car as Car
typedef struct Car Car;


/**
 * Prints brand, model and the date
 * of manufacture of Car to stdout.
 *
 * Argument:
 *    argCar -- Car to read information from.
 *
 * Return:
 * none.
 */
void print( Car argCar );

/**
 * Prints The logo of Car struct to stdout
 * and adds a space at end too.
 *
 * Argument:
 *    argCar -- Car to read information from.
 *
 * Return:
 * none.
 */
void printLogo( Car argCar );

/**
 * Calculates the age of Car by subtracting
 * the current year ( 2017 ) from the Car’s
 * year of manufacture.
 *
 * Argument:
 *    argCar -- Car to read information from.
 *
 * Return:
 * The age of Car passed in as argument.
 */
int calculateAge( Car argCar );

/**
 * Checks if Car is super new, new or old
 * depending on whether it was manufactured
 * within 2 years, 6 years or earlier
 * Argument:
 *    argCar -- Car to read information from.
 *
 * Return:
 * 2 if Car is super new. 1 if new.
 * 0 otherwise.
 */
int isNew( Car argCar );

#endif //CAR_H
