package org.noisesmith;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

import clojure.lang.IFn;
import clojure.java.api.Clojure;

public class Cljsvc implements Daemon {
  static Object ns_name;
  static IFn require;
  static IFn resolve;
  static {
    ns_name = Clojure.read(System.getenv("clojure.app.ns"));
    require = Clojure.var("clojure.core", "require");
    resolve = Clojure.var("clojure.core", "ns-resolve");
  }
  public void init(DaemonContext context)
    throws DaemonInitException, Exception {
    String fn_name = (String) Clojure.read("init");
    Object ns = require.invoke(ns_name);
    IFn ini_fn = (IFn) resolve.invoke(ns_name, fn_name);
    ini_fn.invoke(context);
  }
  public void start() throws Exception {
    IFn prn = Clojure.var("clojure.core", "prn");
    Object data = Clojure.read("{:a 0 :b 1}");
    prn.invoke("hello from clojure", data);
  }
  public void stop() throws Exception {
  }
  public void destroy() {
  }
  public static void main(String[] args) {
    Cljsvc service = new Cljsvc();
    try {
      service.start();
    } catch (Exception e) {
      System.exit(0);
    }
  }
}
