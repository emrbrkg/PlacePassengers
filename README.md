# PlacePassengers
Definition of Project:
  There is a matrice that keeps social distances between 40 people. It is 40x40 square matrice. There is a bus which has 40 seats. and it is represented as 4x10 matrice.
  First we pick a passenger randomly. Then we place this passenger to the first seat. Then we are checking the social distance matrice to find closest person to place to second seat. And do the same for third and fourth seats.
  Then for left window side seat, we are checking the front and front cross seats and calculate the sum of distances of them and place to least distanced passenger to these. 
  For left aiscle side seat, we are checking the left window seat of this line and front left and right cross seats and directly front seat and calculating the sum of distances. Then we are picking the least distances passenger and place to that seat.
  For right aiscle side seat, we are checking the left front cross seat, directly front seat, right front seat and the directly left seat of this line and calculating the sum of the distances and picking the least distanced passenger and place at that seat.
  For right window side seat, we are checking the directly front seat, left front seat, directly left seat and calculating the sum of distances and picking the least distanced passenger and place at that seat.
  This repeats until every seat is placed with a passenger.
  We are printing the names of passengers and seats of passengers and their passenger numbers.







