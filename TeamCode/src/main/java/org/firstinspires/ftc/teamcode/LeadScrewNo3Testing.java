package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "bill", group = "BigBob")



public class LeadScrewNo3Testing extends LinearOpMode {
    final static double ANDYMARK_TICKS_PER_REV = 1120;
    ElapsedTime runtime = new ElapsedTime();

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

    public void runOpMode(){
        robot.init(hardwareMap, telemetry);
         waitForStart();
         while(opModeIsActive()){
             robot.leadScrewUp(1,1,100,this);
             break;
         }


    }
    public void leadScrewUp(double distance, double power, double timeout, LinearOpMode aStop ) {
        robot.resetMotorsAndEncoders();
        int tolerance = 50;
        int counts = (int) Math.round(distance/2 * ANDYMARK_TICKS_PER_REV);
//must set direction first
        robot.leadScrewMotor.setDirection(DcMotor.Direction.REVERSE);
//then set position
        robot.leadScrewMotor.setTargetPosition(counts);
//then set the mode
        robot.leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
        runtime.reset();
        robot.leadScrewMotor.setPower(power);

        while (Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                > tolerance || Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                > tolerance ) {
            if (runtime.seconds() > timeout || !aStop.opModeIsActive()) {
                break;
            }

            telemetry.addData("Current LeadScrew Counts:", (robot.leadScrewMotor.getCurrentPosition()));
            telemetry.addData("LeadScrew Power:", robot.leadScrewMotor.getPower());
            telemetry.addData("LeadScrew Target Pos:", robot.leadScrewMotor.getTargetPosition());
            telemetry.update();
        }
        robot.resetMotorsAndEncoders();
    }
}
