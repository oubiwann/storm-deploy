(ns backtype.storm.crate.leiningen
  (:require
   [pallet.resource.remote-file :as remote-file]
   [pallet.action.exec-script :as exec-script]))

;; this is 2.3.3. freezing version to ensure deploy is stable
(def download-url "https://raw.github.com/technomancy/leiningen/6a6cecee0f32b6b4ffeb3e03280f60efa55c4e2e/bin/lein")

(defn install [request]
  (-> request
      (remote-file/remote-file
       "/usr/local/bin/lein"
       :url download-url
       :owner "root"
       :mode 755)
      (exec-script/exec-script
       (export "LEIN_ROOT=1")
       ("/usr/local/bin/lein"))))
