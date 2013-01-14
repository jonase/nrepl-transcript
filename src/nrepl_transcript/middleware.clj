(ns nrepl-transcript.middleware
  (:import clojure.tools.nrepl.transport.Transport))

(defprotocol Transcript
  (request [t req])
  (response [t resp]))

(defn wrap-transcript [handler t]
  (fn [{:keys [^Transport transport] :as msg}]
    (request t msg)
    (handler (assoc msg
               :transport
               (reify Transport
                 (recv [this]
                   (.recv transport))
                 (recv [this timeout]
                   (.recv transport timeout))
                 (send [this resp]
                   (response t resp)
                   (.send transport resp)))))))
