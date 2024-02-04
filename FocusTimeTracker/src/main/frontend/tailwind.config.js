/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../resources/templates/**/*.html"],
  theme: {
    extend: {
      spacing: {
        112: "28rem",
        128: "32rem",
        144: "36rem",
        160: "40rem",
        176: "44rem",
        192: "48rem",
      },
      fontFamily: {
        sans: ["Lato", "sans-serif"],
      },
      colors: {
        //hover -> 20l or 20d?
        //border -> 20l or 20d?
        //zmiana slate-500 na cos innego to po prostu ctrl+shift+r
        "base-bg": "#0f172a", //slate-900
        "base-border": "#263b6c", //slate-900 20l
        "base-text": "#cbd5e1", //slate-300
        "base-scroll": "#616470",//TODO do zmiany

        "o-1": "#1e293b", //slate-700
        "o-1-border": "#3a4f72", //slate-700 20l

        "form-bg": "#1e293b", //slate-800
        "form-border": "#3a4f72", //slate-800 20l
        "placeholder": "#9ca3af", //gray-400

        "base-button": "#0e7490", //cyan-700
        "base-button-hov": "#155e75", //cyan-800
        "base-line": "#f59e0b", //amber-500
        "light-hov": "#f59e0b", //amber-500
        "error": "#ef4444", //red-500
      }
    },
  },
  plugins: [],
}
