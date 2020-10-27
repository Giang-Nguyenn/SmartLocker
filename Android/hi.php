<?php
$connect = mysqli_connect("localhost","root","","smartlocker");
mysqli_query($connect," SET NAMES 'utf8'");
$query ="SELECT Label,LockerId,Number,Position FROM Lockers
WHERE GroupLockerId IN(SELECT GroupLockerId FROM GroupLockerEmployees
WHERE GroupEmployeeId IN (SELECT GroupEmployeeId FROM Employees
WHERE CodeLogic='30363035383339313830'))";
//30363037303937353030
$data=mysqli_query($connect,$query);
class Employees{
	function Employees($Label,$LockerId,$Number,$Position){
	 $this->Label=$Label;
     $this->LockerId=$LockerId;
     $this->Number=$Number;
     $this->Position=$Position;

	}
}
$mangData=array();
while($row= mysqli_fetch_assoc($data)){
	array_push($mangData, new Employees($row['Label'],$row['LockerId'],$row['Number'],$row['Position']));
}
echo json_encode($mangData);

?>