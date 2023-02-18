$("button.SreachAll").on("click", function () {
    fetch("http://localhost:8080/elitebaby/MealSearch", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                mealId: 2,
            }
        ),
    })
    .then((resp) => {
      if (resp.status === 204) {
        console.log("resp.status===" + resp.status);
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      try {
                if (body.length != null) {
                    $("div.getall").html(body[0].mealId);
                }
            } catch (error) {
                console.log(error + "，資料庫沒照片故後端沒回傳");
            }
    });
})