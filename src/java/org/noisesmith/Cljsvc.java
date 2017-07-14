package org.noisesmith;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

import clojure.lang.IFn;
import clojure.java.api.Clojure;

public class Cljsvc implements Daemon {
  String ns_name = Clojure.read(System.getenv("clojure.app.ns"));
  IFn require = Clojure.var("clojure.core", "require");
  IFn resolve = Clojure.var("clojure.core", "ns-resolve");
  public void init(DaemonContext context)
      throws DaemonInitException, Exception {
    String fn_name = Clojure.read("init");
    IFn ns = require(ns_name);
    IFn ini_fn = resolve(ns_name, fn_name);
    ini_fn.invoke(context);
  }
  public void start() throws Exception {
    IFn prn = Clojure.var("clojure.core", "prn");
    Object data = Clojure.read("{:a 0 :b 1}");
    prn.invoke("hello fro clojure", data);
  }
  public void stop() throws Exception {
  }
  public void destroy() {
  }
}
