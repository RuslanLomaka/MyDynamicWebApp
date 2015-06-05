<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">

<script type="text/javascript">
	function checkempty(f) {
		var errMSG = "";
		// цикл ниже перебирает все элементы в объекте f, 
		// переданном в качестве параметра
		// функции, в данном случае - наша форма.            
		for (var i = 0; i < f.elements.length; i++)
			// если текущий элемент имеет атрибут required
			// т.е. обязательный для заполнения
			if (null != f.elements[i].getAttribute("required"))
				// проверяем, заполнен ли он в форме
				if (isEmpty(f.elements[i].value)) // пустой
					errMSG += "  " + f.elements[i].name + "\n"; // формируем сообщение
		// об ошибке, перечисляя 
		// незаполненные поля
		// если сообщение об ошибке не пусто,
		// выводим его, и возвращаем false     
		if ("" != errMSG) {
			alert("Не заполнены обязательные поля:\n" + errMSG);
			return false;
		}
	}
</script>

<script type="text/javascript">
	function checkForm(form) {
		if (form.username.value == "") {
			alert("Error: Username cannot be blank!");
			form.username.focus();
			return false;
		}
		re = /^\w+$/;
		if (!re.test(form.username.value)) {
			alert("Error: Username must contain only letters, numbers and underscores!");
			form.username.focus();
			return false;
		}

		if (form.pwd1.value != "" && form.pwd1.value == form.pwd2.value) {
			if (form.pwd1.value.length < 6) {
				alert("Error: Password must contain at least six characters!");
				form.pwd1.focus();
				return false;
			}
			if (form.pwd1.value == form.username.value) {
				alert("Error: Password must be different from Username!");
				form.pwd1.focus();
				return false;
			}
			re = /[0-9]/;
			if (!re.test(form.pwd1.value)) {
				alert("Error: password must contain at least one number (0-9)!");
				form.pwd1.focus();
				return false;
			}
			re = /[a-z]/;
			if (!re.test(form.pwd1.value)) {
				alert("Error: password must contain at least one lowercase letter (a-z)!");
				form.pwd1.focus();
				return false;
			}
			re = /[A-Z]/;
			if (!re.test(form.pwd1.value)) {
				alert("Error: password must contain at least one uppercase letter (A-Z)!");
				form.pwd1.focus();
				return false;
			}
		} else {
			alert("Error: Please check that you've entered and confirmed your password!");
			form.pwd1.focus();
			return false;
		}

		alert("You entered a valid password: " + form.pwd1.value);
		return true;
	}
</script>

</head>
<body>

	<!--<section class="container">
	<div class="login">
		<h1>Registration form</h1>

		<form action="RegistrationServlet" method="POST">
			<meta charset="UTF-8">

			<p>
				<input type="text" name="firstName" value=""
					placeholder="First name">
			</p>
			<p>
				<input type="text" name="lastName" value="" placeholder="Last name">
			</p>

			<p>
				<input type="text" name="email" value="" placeholder="email">
			</p>

			<p>
				<input type="text" name="login" value=""
					placeholder="Username (login)">
			</p>

			<p>
				<input type="password" name="password1" value=""
					placeholder="Password">
			</p>
			<p>
				<input type="password" name="password2" value=""
					placeholder="Repeat assword">
			</p>

			<p class="remember_me">
				<label> <input type="checkbox" name="remember_me"
					id="remember_me"> Remember me on this computer
				</label>
			</p>
			<input type="submit" value="Registrate Me">
		</form>




	</div>

	<div class="login-help">
		<p>
			Forgot your password? <a href="index.html">Click here to reset it</a>
		</p>
	</div>
	</section> -->

	<form onSubmit="return checkForm(this)">

		<section class="container">
		<div class="login">
			<form ... onsubmit="checkForm(this)">
				<p>
					<input type="text" name="username" placeholder="Username" required>
				</p>
				<p>
					<input type="password" name="pwd1" placeholder="Password" required>
				</p>
				<p>
					<input type="password" name="pwd2" placeholder="Confirm Password" required>
				</p>
				<p>
					<input type="submit">
				</p>
			</form>
		</div>
		</section>
</body>
</html>