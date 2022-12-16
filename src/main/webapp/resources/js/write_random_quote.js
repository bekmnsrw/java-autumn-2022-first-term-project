writeRandomQuote = function () {
    const quotes = [];
    quotes[0] = "Shop till you drop";
    quotes[1] = "A retail therapy";
    quotes[2] = "Get what you eye";
    quotes[3] = "Pick your favorites";
    const rand = Math.floor(Math.random() * quotes.length);
    document.write(quotes[rand]);
}
