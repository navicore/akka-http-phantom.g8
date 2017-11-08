[![Build Status](https://travis-ci.org/navicore/akka-http-phantom.g8.svg?branch=master)](https://travis-ci.org/navicore/akka-http-phantom.g8)

A [g8] Template for an Akka HTTP API Server persisting objects with [Phantom] for [Cassandra]
---

## PREREQ

  * sbt >= 13.16

## USAGE

G8 will prompt you for details like your project name and package name.

In a terminal shell, enter:

```console
sbt new navicore/akka-http-phantom.g8 
```

`cd` into the resulting directory and test with `sbt run`

notes:

* The defaults assume an unsecured Cassandra at localhost:9042.  You can create a test Cassandra server via `docker run -p 9042:9042 --name my-cassandra -d cassandra:3.11`.
* `sbt assembly` works
* `sbt assembly && docker build -t myimage .` builds a usable Docker image
* see `application.conf` for ENV overrides for Cassandra and Akka settings
* The initial example entity is stored denormalized 
  * Primary key for one model is a v4 UUID
  * Primary key for a second model is a name and clustering key is datetime
  * CRUD 
  * basic time series lookups (last 'n' occurrences of 'name')
  * could be modified to be an append-only event-sourcing backend

[Phantom]: https://github.com/outworkers/phantom
[Cassandra]: http://cassandra.apache.org/
[g8]: http://www.foundweekends.org/giter8/
[g8 setup]: http://www.foundweekends.org/giter8/setup.html 

## DEVELOPING

While developing the template, test using something like:

```console
sbt new file:///Users/navicore/git/navicore/akka-http-phantom.g8
```

