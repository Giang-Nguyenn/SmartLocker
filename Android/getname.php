<?php
$connect = mysqli_connect("localhost","root","","smartlocker");
mysqli_query($connect," SET NAMES 'utf8'");
$query ="SELECT CodeLogic,Name,CreatedAt FROM Employees";
$data=mysqli_query($connect,$query);
class Employees{
	function Employees($CodeLogic,$Name,$CreatedAt){
	 $this->CodeLogic=$CodeLogic;
     $this->Name=$Name;
     $this->CreatedAt=$CreatedAt;

	}
}
$mangData=array();
while($row= mysqli_fetch_assoc($data)){
	array_push($mangData, new Employees($row['CodeLogic'],$row['Name'],$row['CreatedAt']));
}
echo json_encode($mangData);

?>