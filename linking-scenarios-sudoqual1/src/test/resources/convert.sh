#!/bin/sh

for name in $(ls | cut -d '-' -f 1 | uniq); 
do 
    file="../json/$name.json"; 
    echo "{ \"input\": " > $file; 
    cat $name-input.json >> $file; 
    echo ", \"expectedLinks\": [" >> $file; 
    cat $name-expected.json | tail -n+2 >> $file;
    #echo "}" >> $file;  
done