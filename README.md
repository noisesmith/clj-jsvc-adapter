a small wrapper to implement the apache commons Daemon API, using a System
property to find your Clojure namespace at runtime

there is a simple demo under the demo directory, after customizing paths
you can run it via `demo/runit` and request shutdown with `demo/runit -stop`.

logs and a pid file will be created in the working directory
