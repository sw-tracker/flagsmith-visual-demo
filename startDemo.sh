#!/bin/sh
mvn compile package

for((i=1; i<=15; i++))
do
  echo "Starting java program $i"
  java -jar shade/hellofx.jar --id=$i &
done

echo "Press 'q' to exit"
while : ; do
  read -n 1 k <&1
  if [[ $k = q ]] ; then
    printf "\nQuitting from the program\n"
    break
  fi
done

echo "Trying to kill all programs..."
pkill -f "hellofx"
echo "Programs killed successfully!"