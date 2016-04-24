<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($firstname,$lastname,$email,$password)
		{
			
				$query = "insert into USERS (user_id,firstname,lastname,email,password) values ('', '$firstname','$lastname','$email','$password')";
				$inserted = mysqli_query($this -> connection, $query);
				if($inserted == 1 ){
					$json['success'] = 'Account created';
				}else{
					$json['error'] = ' Failed to Register';
				}
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		

	
	
	$user = new User();
	if(isset($_POST['firstname'],$_POST['lastname'],$_POST['email'],$_POST['password'])) {
	        $firstname= $_POST['firstname'];
                $lastname = $_POST['lastname'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		
		if(!empty($email) && !empty($password) && !empty($firstname) && !empty($lastname)){
			
			$encrypted_password = md5($password);
			$user-> does_user_exist($firstname,$lastname,$email,$password);
			
		}else{
			echo json_encode("you must provide all the inputs");
		}
		
	}
?>