/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;



/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 * <p>
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Arm TestCs Android Studio", group = "Arm")
public class ArmCS extends LinearOpMode {
    //HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    /* Declare OpMode members. */
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor BucketMotor = null;
    private Servo BucketServo = null;
    @Override
    public void runOpMode() {
        //robot.init(hardwareMap, telemetry);
        BucketMotor = hardwareMap.get(DcMotor.class, "bucket_Motor");
        BucketServo = hardwareMap.get(Servo.class, "bucket_Servo");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        int increment = 10;
        int encoderValue = 0;
        int max_arm_position = 3300;
        int min_arm_position = 0;
        double arm_motor_power = 0.5;

        waitForStart();
        leftDrive.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightDrive.setMode(DcMotor.RunMode.RESET_ENCODERS);
        while (opModeIsActive()) {
     /*       double leadScrewPower;
            double leadScrewUpPower = gamepad1.left_trigger * 0.7;
            double leadScrewDownPower = -gamepad1.right_trigger * 0.7;
            leadScrewPower = Range.clip(leadScrewUpPower + leadScrewDownPower, -1.0, 1.0);

            if (leadScrewUpPower > leadScrewDownPower) {
                robot.leadScrewMotor.setPower(leadScrewPower);
            } else if ((leadScrewDownPower > leadScrewUpPower)) //&& robot.LimitSwitchLsBottom.getState() == false)
            {
                robot.leadScrewMotor.setPower(leadScrewPower);
            } else {
                robot.leadScrewMotor.setPower(0);
            }
*/

            //Tank Drive with Joystick

          /*  double drivePower = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 2;
            double rotPwr = gamepad1.right_stick_x * 0.7;
*/
            if (gamepad1.dpad_up && encoderValue <= max_arm_position) {
                encoderValue += increment;
            } else if (gamepad1.dpad_down && encoderValue > min_arm_position) {
                encoderValue -= increment;
            }

            leftDrive.setTargetPosition(encoderValue);
            rightDrive.setTargetPosition(encoderValue);

            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftDrive.setPower(Math.abs(arm_motor_power));
            rightDrive.setPower(Math.abs(arm_motor_power));
            if (gamepad1.left_bumper) {
                BucketMotor.setPower(1);
            } else if (gamepad1.right_bumper) {
                BucketMotor.setPower(-1);
            } else if (gamepad1.y) {

                BucketServo.setPosition(.7);
            } else if (gamepad1.b) {

                BucketServo.setPosition(.2);
            } else {
                BucketServo.setPosition(.5);
                BucketMotor.setPower(0);
            }
            telemetry.addData("Encoder value", encoderValue);
            telemetry.addData("Encoder value on MotorL", leftDrive.getCurrentPosition());
            telemetry.addData("Encoder value on MotorR", rightDrive.getCurrentPosition());
            telemetry.addData("Motor powerL ", "%5.2f", leftDrive.getPower());
            telemetry.addData("Motor powerR ", "%5.2f", rightDrive.getPower());
            telemetry.update();


        }


    }
}