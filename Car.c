/**
 * Including C libraries
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Including function prototypes
 */
#include "Car.h"

/**
 * Definition of constants
 */
#define INDEX_OF_DOM 6
#define CURRENT_YEAR 2017
#define SUPER_NEW_YEAR 2
#define NEW_YEAR 6
#define THOUSAND 6
#define HUNDRED 7
#define TEN 8
#define LOW 9

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
void print( Car argCar ) {
    printf("Brand: %s\n", argCar.brand);
    printf("Model: %s\n", argCar.model);
    printf("Date of manufacture: %s\n", argCar.date);
    
}

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
void printLogo( Car argCar ) {
  //Getting the first letter of the brand and the model
  //Hint: think string as array of characters.
    char logo[4];
    logo[0]=argCar.brand[0];
    logo[1]='.';
    logo[2]=argCar.model[0];
    logo[3]='.';
    printf("%s",logo);
}

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
int calculateAge( Car argCar ) {
  //Getting the year of manufacture only
  //Hint: think string as array of characters.

    
    //string some;
    //for (int i=INDEX_OF_DOM; i< sizeof(argCar.date) ; i++){
    //        some += argCar.date[i];
    //}
    
    
    
    
     int temp = 0;
     temp += (argCar.date[THOUSAND]-48)*1000;
     temp += (argCar.date[HUNDRED]-48)*100;
     temp += (argCar.date[TEN]-48)*10;
     temp += (argCar.date[LOW]-48)*1;
    
    return CURRENT_YEAR - temp;
}

/**
 * Checks if Car is super new, new or old
 * depending on whether it was manufactured
 * within 2 years, 6 years or earlier
 * Argument:
 *    argCar -- Car to read information from.
 *
 * Return:
 * 2 if Car is super new. 1 if new.
 * 0 if old.
 */
int isNew( Car argCar ){
    int tempyear = calculateAge(argCar);
    
    if(tempyear <= SUPER_NEW_YEAR){
        return 2;
    }
    else if(tempyear <= NEW_YEAR && tempyear > SUPER_NEW_YEAR){
        return 1;
    }
    else{
        return 0;
    }

}
