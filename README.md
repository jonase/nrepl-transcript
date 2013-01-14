# nrepl-transcript

[nREPL](https://github.com/clojure/tools.nrepl) middleware to save a transcript of your repl interactions.

## Usage

Update your `project.clj` with

```clojure
(defproject your-project
  :dev-dependencies [...
                     [nrepl-transcript "0.1.0"]
                     ...]
  ...
  :repl-options {:nrepl-middleware
                 [nrepl-transcript.file/wrap-file-transcript]}
  ...)
```

and all your interactions will appear in a "transcript.txt" file in the root of your project.

### System wide usage

You can also add the followings to `~/.lein/profiles.clj` to record repl interactions system wide:

```clojure
{:user {...
        :dependencies [[nrepl-transcript "0.1.0"]]
        :repl-options {:nrepl-middleware
                       [nrepl-transcript.file/wrap-file-transcript]}}
        ...}
```


## TODO
* capture `*out*`

## License

Copyright © 2013 Jonas Enlund

Distributed under the Eclipse Public License, the same as Clojure.
