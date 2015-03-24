## Running

`git clone https://github.com/dubiousdavid/macros-talk.git`

Run `lein gorilla` then type `C-g C-l` in the worksheet to load 'presentation.clj'.

## Clearing Output

Run `lein repl`

```clojure
(use 'parse)
(clear-output "presentation.clj")
```

Refresh worksheet (reload your browser).
