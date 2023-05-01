function onBtnClick(e) {
    let color = e.textContent;
    let myDiv = $('#myDiv');
    myDiv.css("background-color", color)
}