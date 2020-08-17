# I'll try to explain the idea and the functions

So basically, this is my first data clustering's class homework.
The idea is to receive a .csv file with missing values, estimate those missing values and "correct" the file.

## isNumeric

This function's tries to parse a String to Double, if it doesn't return a exception it means it is a numeric String.

## findMedia

This function receives an array of strings, change them to numbers, find and return the mean in the form of a string.

## findModa

This function receives an array of strings, finds the most frequent element in that array and returns it.

## writeToFile

This function receives an arrayList of lists of strings, and word by word, line by line, it writes it to an existing file or creates a new one

## readEstimateCorrect

This function opens the .csv file, read it to an arrayList of lists of strings, find the missing values, estimates them using the findMedia and
findModa functions. Finally it returns the arrayList so that the writeToFile can do it's job


