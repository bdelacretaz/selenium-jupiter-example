// Minimal JavaScript to have dynamic content
var e = document.createElement("div");
e.innerHTML = "This is dynamic content, at " + new Date();
document.getElementById("dynamic").appendChild(e);