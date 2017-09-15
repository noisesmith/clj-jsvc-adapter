package org.noisesmith;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

import clojure.lang.IFn;
import clojure.java.api.Clojure;

public class Cljsvc implements Daemon {
  static String ns_name = System.getenv("clojure.app.ns");
  static IFn require = Clojure.var("clojure.core", "require");
  public void init(DaemonContext context)
    throws DaemonInitException, Exception {
    Object ns_sym = Clojure.read(ns_name);
    Object ns = require.invoke(ns_sym);
    IFn ini_fn = Clojure.var(ns_name, "init");
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
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}
