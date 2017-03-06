# Pilot for Jumping Sumo

This application is a pilot for Parrot Jumping Sumo 
that will read its input and send its output 
to Hadoop.


# Prerequisites

## The Driver

It requires the DroneController v0.2.0a

Since this jar it is not available on maven centra as a binary, 
you have to build and publish it locally.

So do this:

```
git clone https://github.com/dronocode/drone-controller
cd drone-controller
mvn install
```

## Point to your Hadoop

You need a working HDFS server accessibile by the pilot.

Copy the config.sbt.dist and edit it, setting system properties properly.

## Build and Run

Use sbt to compile and run it.

```
sbt run
```

It will 