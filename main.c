/**
 * Addind C Standard library
 */
#include <stdio.h>
#include <stdlib.h>

/**
 * Adding local header files
 */
#include "Car.h"

/**
 * Definition of constants
 */
#define NUM_ARGS 4

/**
 * Main method to make a Car struct, populate it and
 * call helper methods to print Car information.
 * 
 * Arguments:
 *     argc -- Holds the number of command line
 *             arguments passed.
 *     argv -- Holds each command line argument as
 *             char string.
 *
 * Error:
 * If we don't pass in 3 command line arguments, write an error to stderr
 * and terminate the program
 *
 * Return:
 * EXIT_FAILURE upon hitting an error. EXIT_SUCCESS on successful program run.
 */
int main( int argc, char* argv[] ) {
	
  //Checks if we pass in 3 arguments
  if( argc != NUM_ARGS ) {
		fprintf( stderr, "Program only accepts 3 command line arguments.\n" );
		return EXIT_FAILURE;
	}

  //Make and populate Car struct from command line arguments
    Car myCar = {argv[1],argv[2],argv[3]};

  //Print Car’s logo followed by Car’s information

  //TODO: logo first
    printLogo(myCar);

  printf( "Information:\n" );

  //then print Car’s information:
  //Print brand and model followed by whether the Car is a super new, new or old.
	//TODO
    print(myCar);

    int newcar = isNew(myCar);
    if(newcar == 2){
        printf("%s %s is super new!\n",myCar.brand,myCar.model);
    }
    else if(newcar == 1){
        printf("%s %s is new!\n",myCar.brand,myCar.model);
    }
    else{
        printf("%s %s is old!\n",myCar.brand,myCar.model);
    }

  return EXIT_SUCCESS;
}
