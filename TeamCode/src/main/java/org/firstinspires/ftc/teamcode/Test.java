package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "test", group = "test")

public class Test extends LinearOpMode{

    public DcMotor ArmMotor = null;

    public void runOpMode() {
        ArmMotor = hardwareMap.get(DcMotor.class, "left_FrontMotor");
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.a) {
                ArmMotor.setPower(0.5);
            } else if(gamepad1.b) {
                ArmMotor.setPower(-0.5);
            }
        }

    }

}
