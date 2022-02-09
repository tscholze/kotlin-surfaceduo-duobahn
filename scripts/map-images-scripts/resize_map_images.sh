#!/bin/sh

# This script resized Autobahn maps to a more useable one.
#
# Example:
#
#   Input file dimension: 
#   „1073 × 1272“
#
#   Output file dimension: 
#   "253 x 300"

# Ensure ImageMagick's convert is installed
if ! command -v convert /dev/null
then
    echo "ABORT: ImageMagick's convert is required to run this script."
    echo "-> Install imagemagick on macOS using >brew install imagemagick<"
    exit
fi

for file in *.png; do 
    convert -resize x300 $file $file
done

echo "\n\nIt is recommanded to run an app like >ImageOptim< to reduce the file size of the resized images."