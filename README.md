a small wrapper to implement the apache commons Daemon API, using a System
property to find your Clojure namespace at runtime

there is a simple demo under the demo directory, after customizing paths
and running `lein uberjar` to create a standalone artifact, you can run it
via `demo/runit` and request shutdown with `demo/runit -stop`.

logs and a pid file will be created in the working directory

for usage in your own project, build and cache a jar with `lein install`, and
build your own jar with this dependency, starting jsvc with your own fat jar
on the classpath
