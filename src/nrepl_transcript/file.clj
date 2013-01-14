(ns nrepl-transcript.file
  (:require [clojure.java.io :as io]
            [nrepl-transcript.middleware :refer [wrap-transcript]])
  (:import [nrepl_transcript.middleware Transcript]
           [java.util Date]))

(defonce filename "transcript.txt")

(spit filename (str "\n*** " (pr-str (Date.)) " ***\n") :append true)


(defn file-transcript [filename]
  (reify Transcript
    (request [_ req]
      (when (= "eval" (:op req))
        (spit filename
              (str "=> " (:code req))
              :append true)))
    (response [_ resp]
      (when-let [v (:value resp)]
        (spit filename
              (str v \newline)
              :append true)))))

(defn wrap-file-transcript [handler]
  (wrap-transcript handler (file-transcript filename)))
