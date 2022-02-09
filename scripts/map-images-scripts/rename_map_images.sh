#!/bin/sh

# This script converts the original name of Autobahn maps 
# to a more useable one.
#
# Example:
#   File source:
#   https://de.wikipedia.org/wiki/Liste_der_Bundesautobahnen_in_Deutschland
#
#   Input file name: 
#   „1073px-Map_D_A67.svg.png“
#
#   Desired output file name:
#   "a67.png"

for file in *.png; do 
    newname=$(echo "$file" | sed -n 's/1073px-Map_D_A\(.*\)\.svg\.png/map_a\1.png/p'); 
    mv "$file" "$newname"; 
done
