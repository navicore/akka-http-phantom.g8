[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1bec982da37d4df0a375d183f2b97dd6)](https://www.codacy.com/app/navicore/akka-http-phantom.g8?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=navicore/akka-http-phantom.g8&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/navicore/akka-http-phantom.g8.svg?branch=master)](https://travis-ci.org/navicore/akka-http-phantom.g8)

A [g8] Template for an Akka HTTP API Server persisting objects with [Phantom] for [Cassandra]
---

## PREREQ

  * sbt >= 13.16


## USAGE

G8 will prompt you for details like your project name and package name

In a terminal shell, enter:

```console
sbt new navicore/akka-http-phantom.g8 
```

`cd` into the resulting directory and test with `sbt compile`

See the generated README.md for how to configure it for Cassandra, run, build, etc...


## DEVELOPING

While changing the template, test using something like:

```console
sbt new file:///Users/navicore/git/navicore/akka-http-phantom.g8
```

[Phantom]: https://github.com/outworkers/phantom
[Cassandra]: http://cassandra.apache.org/
[g8]: http://www.foundweekends.org/giter8/
[g8 setup]: http://www.foundweekends.org/giter8/setup.html 

