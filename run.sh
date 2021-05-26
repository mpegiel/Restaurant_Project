#!/bin/bash
mvn compile
mvn exec:java -Dexec.mainClass=pl.agh.restaurant_project.RestaurantProjectApplication
