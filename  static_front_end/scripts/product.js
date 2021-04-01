function create_new_book(id) {
    let father = document.getElementById(id)

    let new_book = document.createElement("div");

    let a_img = document.createElement("a");
    a_img.href = "details.html";
    let img = document.createElement("img");
    img.src = "images/ml.jpg";
    img.style.width = "244px";
    img.style.height = "350px";
    a_img.appendChild(img);
    new_book.appendChild(a_img);

    let prize = document.createElement("p")
    prize.style.color = "orange";
    let prize_text = document.createTextNode("￥ 40 包邮");
    prize.appendChild(prize_text);
    new_book.appendChild(prize);

    let book_name = document.createElement("p")
    let book_name_text = document.createTextNode("深入理解机器学习")
    book_name.appendChild(book_name_text);
    new_book.appendChild(book_name);

    let book_store = document.createElement("p");
    book_store.style.color = "gray";
    let book_store_name = document.createTextNode("bookstore");
    book_store.appendChild(book_store_name);
    new_book.appendChild(book_store);

    let assess = document.createElement("p");
    assess.style.color = "gray";
    assess.align = "right";
    let assess_text = document.createTextNode("如实描述4.8");
    assess.appendChild(assess_text);
    new_book.appendChild(assess);


    father.appendChild(new_book);
}


