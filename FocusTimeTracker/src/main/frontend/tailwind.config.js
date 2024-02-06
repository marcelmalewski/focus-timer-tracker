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
        //Base:
        // sky900, sky700, sky500
        // cyan600,
        // slate300, slate100
        // amber300
        "base-bg": "#0c4a6e", //sky-900
        "base-text": "#f1f5f9", //slate-100
        "base-line": "#fcd34d", //amber-300

        "form-bg": "#0369a1", //sky-700
        "form-border": "#0ea5e9", //sky-500

        "base-button": "#0891b2", //cyan-600
        "base-button-hov": "#0e7490", //cyan-700

        "error": "#4c0519",
      }
    },
  },
  plugins: [],
}
