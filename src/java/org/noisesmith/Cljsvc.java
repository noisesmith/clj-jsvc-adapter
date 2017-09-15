package org.noisesmith;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

import clojure.lang.IFn;
import clojure.java.api.Clojure;

public class Cljsvc implements Daemon {
  static String ns_name = System.getProperty("clojure.app.ns");
  static IFn require = Clojure.var("clojure.core", "require");
  static Object ns_sym = Clojure.read(ns_name);
  static Object ns = require.invoke(ns_sym);

  public void init(DaemonContext context)
      throws DaemonInitException, Exception {
      IFn ini_fn = Clojure.var(ns_name, "init");
      ini_fn.invoke(context);
  }

  public void start() throws Exception {
    IFn start_fn = Clojure.var(ns_name, "start");
    start_fn.invoke();
  }

  public void stop() throws Exception {
    IFn stop_fn = Clojure.var(ns_name, "stop");
    stop_fn.invoke();
  }

  public void destroy() {
    IFn destroy_fn = Clojure.var(ns_name, "destroy");
    destroy_fn.invoke();
  }

  public static void main(String[] args) {
    Cljsvc service = new Cljsvc();
    try {
      service.start();
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      try {
        service.stop();
      } catch (Exception e2) {
        System.err.println(e2.getMessage());
        e.printStackTrace();
        service.destroy();
      } finally {
        System.exit(1);
      }
    }
  }
}
