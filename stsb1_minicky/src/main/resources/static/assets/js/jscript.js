/* 
$('#login').click(function(){
  		$('.php-email-form').submit();
  	}); 
*/
 
document.getElementById("login").addEventListener("click", function(event) {
    // Get the values of the userid and userpw fields
    var userid = document.getElementById("userid").value;
    var userpw = document.getElementById("userpw").value;
    
 	// Initialize a variable to track validation status
    var valid = true;

    // Check userid
    if (userid.trim() === "") {
        alert("사용자 아이디를 입력하시오.");
        valid = false;
        return;
    }

    // Check the userpw
    if (userpw.trim() === "") {
        alert("사용자 비밀번호를 입력하시오.");
        valid = false;
        return;
    }

    // If validation fails, prevent the form from submitting
    if (!valid) {
        event.preventDefault();
    } else {
        // If validation passes, submit the form
        document.querySelector(".php-email-form").submit();
    }
});

/*
$('#regmem').click(function(){
	$('.php-email-form').submit();
});
*/
// check duplicate userid
function checkDuplicate() {
    // Get the user ID from the input field
    var userId = document.getElementById("userid").value;
    var messageElement = document.getElementById("useridHelp");
    
    // Check userid
    if (userId.trim() === "") {
        alert("사용자 아이디를 입력하시오.");        
        return;
    }
     
    // Make an Axios POST request to the Spring REST API
	axios.post('/api/membercheck', {
        userid: userId
    })
    .then(function (response) {
        if (response.data.exists) {
            messageElement.textContent = "아이디가 이미 사용 중입니다. 다른 아이디를 선택해주세요.";
            messageElement.classList.add("text-danger");
            messageElement.classList.remove("text-success");
        } else {
            messageElement.textContent = "아이디를 사용 가능합니다.";
            messageElement.classList.add("text-success");
            messageElement.classList.remove("text-danger");
        }
    })
    .catch(function (error) {
        // Handle errors such as network issues or API errors
        console.error("There was an error checking the ID:", error);
        alert("중복 체크 중 오류가 발생했습니다. 다시 시도해주세요.");
    });
}