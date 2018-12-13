
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class HardwareHexbotRoverRuckus {

    /* Public OpMode members. */

    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor leftRearMotor = null;
    public DcMotor rightRearMotor = null;
    public DcMotor leadScrewMotor = null;
    public DcMotor ArmMotor = null;
    public DcMotor LinkMotor = null;
    public DcMotor BucketMotor = null;
    public Servo BucketServo = null;

    public Servo TeamMarker = null;

    public DigitalChannel LimitSwitchLinkBottom;
    public DigitalChannel LimitSwitchLinkTop;
    public DigitalChannel LimitSwitchLsBottom;

    BNO055IMU imu;
    Orientation angles;
    Telemetry localtelemetry;
    ElapsedTime runtime = new ElapsedTime(); //what are we doing here

    final static double ANDYMARK_TICKS_PER_REV = 1120;

    final static double WormGearRatio = 20;
    final static double TickPerDeg = (ANDYMARK_TICKS_PER_REV * WormGearRatio) / 360;
    final static double rotation = ANDYMARK_TICKS_PER_REV*3;
    final static double WHEEL_DIAMETER = 3.97401575;
    final static double ArmFinalPosition = 13500; //160 * TickPerDeg;
    final static double ArmLiftPosition = 8500; //80* TickPerDeg;
    final static double ArmHomePosition = 0;
    final static double LinkFinalPosition = 966;// (90 * TickPerDeg)/8 ;
    final static double LinkHomePosition = 0;
    final static double BucketHomePosition = .37;
    final static double COUNTS_PER_INCH = ANDYMARK_TICKS_PER_REV / (WHEEL_DIAMETER * Math.PI);






    HardwareMap hwMap = null;




    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        localtelemetry = telemetry;

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Map tank Motors to hardware
        leftFrontMotor = hwMap.get(DcMotor.class, "left_FrontMotor");
        leftRearMotor = hwMap.get(DcMotor.class, "left_RearMotor");
        rightFrontMotor = hwMap.get(DcMotor.class, "right_FrontMotor");
        rightRearMotor = hwMap.get(DcMotor.class, "right_RearMotor");

        //Map  arm Motors to Hardware
        ArmMotor = hwMap.get(DcMotor.class, "arm_Motor");
        LinkMotor = hwMap.get(DcMotor.class, "link_Motor");
        BucketMotor = hwMap.get(DcMotor.class, "bucket_Motor");

        // Define and Initialize lead screw Motor

        leadScrewMotor = hwMap.get(DcMotor.class, "lead_ScrewMotor");

        // Define and initialize ALL installed servos.

        BucketServo = hwMap.get(Servo.class, "bucket_Servo");
        TeamMarker = hwMap.get(Servo.class, "tm_Servo");



        /*LimitSwitchLinkBottom = hwMap.get(DigitalChannel.class, "SwitchLinkBottom");
        LimitSwitchLinkTop = hwMap.get(DigitalChannel.class, "SwitchLinkTop");
        LimitSwitchLsBottom = hwMap.get(DigitalChannel.class, "SwitchLsBottom");*/

        /*LimitSwitchLinkBottom.setMode(DigitalChannel.Mode.INPUT);
        LimitSwitchLinkTop.setMode(DigitalChannel.Mode.INPUT);
        LimitSwitchLsBottom.setMode(DigitalChannel.Mode.INPUT);*/

        //adding rev imu (gyro,accelerometer,etc.)
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        runtime.reset();

        resetMotorsAndEncoders();
        setMotorDirections();
    }
    //----------------------------------------------------------------------------------------------
    // Methods to Reset Motors and Encoders
    //----------------------------------------------------------------------------------------------

    public void setMotorDirections() {

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);
        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
        LinkMotor.setDirection(DcMotor.Direction.FORWARD);
        leadScrewMotor.setDirection(DcMotor.Direction.FORWARD);
        BucketMotor.setDirection(DcMotor.Direction.FORWARD);

    }

    public void resetMotorsAndEncoders() {
        resetMotors();
        resetEncoderValues();
    }


    public void resetMotors() {
        leftRearMotor.setPower(0);
        leftFrontMotor.setPower(0);
        rightRearMotor.setPower(0);
        rightFrontMotor.setPower(0);
        BucketMotor.setPower(0);
        LinkMotor.setPower(0);
        ArmMotor.setPower(0);
        leadScrewMotor.setPower(0);
    }

    public void resetEncoderValues() {
        setEncoderMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setEncoderMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void setEncoderMode(DcMotor.RunMode mode) {
        leftRearMotor.setMode(mode);
        leftFrontMotor.setMode(mode);
        rightRearMotor.setMode(mode);
        rightFrontMotor.setMode(mode);
        LinkMotor.setMode(mode);
        ArmMotor.setMode(mode);
        leadScrewMotor.setMode(mode);
    }


    public void resetServo() {
        BucketServo.setPosition(BucketHomePosition);

    }



    public void setDriveMotorBehaviorToBrake(){
        leftRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    //----------------------------------------------------------------------------------------------
    // Methods for Gyro  http://stemrobotics.cs.pdx.edu/node/7265
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    // Angle Measurement Formatting
    //----------------------------------------------------------------------------------------------

    public double getCurrentAngle() {
        double anglesNorm;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        anglesNorm = NormalizeDegrees(angles.angleUnit, angles.firstAngle);
        return anglesNorm;
    }


    public double NormalizeDegrees(AngleUnit angleUnit, double angle) {
        double degrees;
        degrees = AngleUnit.DEGREES.fromUnit(angleUnit, angle);
        degrees = AngleUnit.DEGREES.normalize(degrees);
        return degrees;
    }



    // Method to figure out correction value


    public double gyroCorrection( double targetAngle, double correctionFactor)
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle;
        double gain = correctionFactor; // start with 0.1

        angle = getCurrentAngle();

        if (Math.abs(angle - targetAngle)<= 1)
            correction = 0;             // no adjustment.
        else
            correction = angle - targetAngle;        // reverse sign of angle for correction.

        correction = Range.clip(correction * gain,-0.7,0.7);

        return correction ;
    }

    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // Methods for Drive Motors




    public void tankRotate ( double targetAngle, LinearOpMode aStop)
    {
        resetMotorsAndEncoders();


        double wheelSpeeds[] = new double[4];

        setMotorDirections();

        double rotPwr =0.3;

        double startingAngle = getCurrentAngle();
        double finalAngle = targetAngle;
        double tolerance = 3;
        //wrap final Angle to +/- 180
/*        if (finalAngle > 180)
            finalAngle -= 360;
        if (finalAngle <= -180)
            finalAngle += 360;*/

        setEncoderMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (finalAngle < startingAngle) {
            wheelSpeeds[0]  =   rotPwr;
            wheelSpeeds[1]  =    rotPwr;
            wheelSpeeds[2]  =    rotPwr;
            wheelSpeeds[3]  =    rotPwr;
        } else {
            wheelSpeeds[0]  =   - rotPwr;
            wheelSpeeds[1]  =     -rotPwr;
            wheelSpeeds[2]  =    - rotPwr;
            wheelSpeeds[3]  =    - rotPwr;
        }

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        runtime.reset();

     /*   while (runtime.seconds() < 10) {

            localtelemetry.addData("Run Time:", runtime.seconds());
            localtelemetry.addData("Heading:", getCurrentAngle());
            localtelemetry.addData("start angle:", startingAngle);
            localtelemetry.addData("final angle:", finalAngle);
            localtelemetry.addData("LeftMotorCurrent position", leftFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", leftRearMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightRearMotor.getPower());

            localtelemetry.update();

        }*/


        while ((Math.abs(finalAngle - getCurrentAngle()) > tolerance) && aStop.opModeIsActive()) {
            localtelemetry.addData("Heading:", getCurrentAngle());
            localtelemetry.addData("start angle:", startingAngle);
            localtelemetry.addData("final angle:", finalAngle);
            localtelemetry.addData("LeftMotorCurrent position", leftFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", leftRearMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightRearMotor.getPower());

            localtelemetry.update();
        }

        resetMotorsAndEncoders();
        setDriveMotorBehaviorToBrake();

    }




    public void tankDrivecs (double drivePower, double robotAngle, int inches, double timeout, LinearOpMode aStop)
    {
        double angleInRad = (robotAngle + 180)*(Math.PI/180);
        int counts = (int) Math.round(COUNTS_PER_INCH * inches);
        int tolerance = 50;

        double wheelSpeeds[] = new double[4];

//must set direction first
        setMotorDirections();
        wheelSpeeds[0]  =   (drivePower* Math.sin(angleInRad + Math.PI/4));
        wheelSpeeds[1]  =   -(drivePower*  Math.cos(angleInRad + Math.PI/4));
        wheelSpeeds[2]  =   (drivePower* Math.cos(angleInRad + Math.PI/4));
        wheelSpeeds[3]  =   -(drivePower*  Math.sin(angleInRad + Math.PI/4));


        int wheelCounts[]= new int[4];

        wheelCounts[0]  =  (int)(counts* wheelSpeeds[0]);
        wheelCounts[1]  =  (int)(counts*  wheelSpeeds[1]);
        wheelCounts[2]  =  (int)(counts* wheelSpeeds[2]);
        wheelCounts[3]  =  (int)(counts*  wheelSpeeds[3]);

//then set position

        leftFrontMotor.setTargetPosition(wheelCounts[0]);
        rightFrontMotor.setTargetPosition(wheelCounts[1]);
        leftRearMotor.setTargetPosition(wheelCounts[2]);
        rightRearMotor.setTargetPosition(wheelCounts[3]);


//then set the mode
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        /*while (leftFrontMotor.isBusy() || rightFrontMotor.isBusy() || leftRearMotor.isBusy() || rightRearMotor.isBusy())  {
*/          while ((Math.abs(leftFrontMotor.getTargetPosition() - leftFrontMotor.getCurrentPosition()) > tolerance) ||
            (Math.abs(rightFrontMotor.getTargetPosition() - rightFrontMotor.getCurrentPosition()) > tolerance) ||
            (Math.abs(leftRearMotor.getTargetPosition() - leftRearMotor.getCurrentPosition()) > tolerance) ||
            (Math.abs(rightRearMotor.getTargetPosition() - rightRearMotor.getCurrentPosition()) > tolerance))
             {
            if (runtime.seconds() > timeout || !aStop.opModeIsActive()) {
                break;
            }

            // Display it for the driver.
            localtelemetry.addData("Left F , Right F",  "Running to %7d :%7d", wheelCounts[0],  wheelCounts[1]);
            localtelemetry.addData("Left R , Right R",  "Running to %7d :%7d", wheelCounts[2],  wheelCounts[3]);
            localtelemetry.addData("Left F , Right F",  "Running at %7d :%7d", leftFrontMotor.getCurrentPosition(), rightFrontMotor.getCurrentPosition());
            localtelemetry.addData("Left R , Right R",  "Running at %7d :%7d", leftRearMotor.getCurrentPosition(), rightRearMotor.getCurrentPosition());
           // localtelemetry.addData("hi", leftFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", leftFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", leftRearMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightRearMotor.getPower());
            localtelemetry.update();
        }

        resetMotorsAndEncoders();
        setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //__End_cs_____________________________________________________________________________________________


    //-----------------------------------------------------------------------------------------------------

    public void tankDrive2(double drivePower, double robotAngle, double rotPwr, int inches, LinearOpMode aStop)
    {
        double angleInRad = (robotAngle + 180)*(Math.PI/180);

        double wheelSpeeds[] = new double[4];
        int counts = (int) Math.round(COUNTS_PER_INCH * inches);
//must set direction first
        setMotorDirections();
        wheelSpeeds[0]  =   (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =   -(drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =   (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =   -(drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

//then set position
        if (wheelSpeeds[0] >= 0) {
            leftFrontMotor.setTargetPosition(counts);
        }
        else
        {
            leftFrontMotor.setTargetPosition(-counts);
        }
        if (wheelSpeeds[1] >= 0) {
            rightFrontMotor.setTargetPosition(counts);
        }
        else
        {
            rightFrontMotor.setTargetPosition(-counts);
        }
        if (wheelSpeeds[2] >= 0) {
            leftRearMotor.setTargetPosition(counts);
        }
        else
        {
            leftRearMotor.setTargetPosition(-counts);
        }
        if (wheelSpeeds[3] >= 0) {
            rightRearMotor.setTargetPosition(counts);
        }
        else
        {
            rightRearMotor.setTargetPosition(-counts);
        }

//then set the mode
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        runtime.reset();

        while ((leftFrontMotor.getCurrentPosition () < leftFrontMotor.getTargetPosition()) && aStop.opModeIsActive()) {
            localtelemetry.addData("LeftMotorCurrent position", leftFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", leftRearMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent position", rightRearMotor.getPower());
            localtelemetry.update();
        }
        resetMotors();
    }//----------------------------------------------------------------------------------------------


    public void tankDrive(double drivePower, double robotAngle, double rotPwr,double duration,LinearOpMode aStop)
    {
        double angleInRad = (robotAngle + 180)*(Math.PI/180);

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  =   (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =   -(drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =   (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =   -(drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        runtime.reset();

        while ((runtime.seconds () < duration) && aStop.opModeIsActive()) {
            localtelemetry.addData("LeftMotorCurrent Power", leftFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent Power", rightFrontMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent Power", leftRearMotor.getPower());
            localtelemetry.addData("LeftMotorCurrent Power", rightRearMotor.getPower());
            localtelemetry.update();
        }
        resetMotors();
    }
    public void tankDrive(double drivePower, double robotAngle, double rotPwr)
    {

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  = (drivePower* Math.sin(robotAngle + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =  - (drivePower*  Math.cos(robotAngle + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =  (drivePower* Math.cos(robotAngle + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =  - (drivePower*  Math.sin(robotAngle + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(scaleInput(wheelSpeeds[0]));
        rightFrontMotor.setPower(scaleInput(wheelSpeeds[1]));
        leftRearMotor.setPower(scaleInput(wheelSpeeds[2]));
        rightRearMotor.setPower(scaleInput(wheelSpeeds[3]));

    }



    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.40, 0.50, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }



    public void normalize(double[] wheelSpeeds)

    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++)
        {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude)
            {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0)
        {
            for (int i = 0; i < wheelSpeeds.length; i++)
            {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }

//----------------------------------------------------------------------------------------------
    // Methods for Lead Screw
    //----------------------------------------------------------------------------------------------

    public void leadScrewUp(double distance, double power, double timeout, LinearOpMode aStop ) {
        resetMotorsAndEncoders();
        int tolerance = 50;
        int leadScrewPitch = 2;
        int counts = (int) Math.round(distance/leadScrewPitch * ANDYMARK_TICKS_PER_REV);
//must set direction first
        leadScrewMotor.setDirection(DcMotor.Direction.FORWARD);
//then set position
        leadScrewMotor.setTargetPosition(counts);
//then set the mode
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
        runtime.reset();
        leadScrewMotor.setPower(power);

        while (Math.abs(leadScrewMotor.getTargetPosition() - leadScrewMotor.getCurrentPosition())
                > tolerance || Math.abs(leadScrewMotor.getTargetPosition() - leadScrewMotor.getCurrentPosition())
                > tolerance ) {
            if (runtime.seconds() > timeout || !aStop.opModeIsActive()) {
                break;
            }

            localtelemetry.addData("Current LeadScrew Counts:", (leadScrewMotor.getCurrentPosition()));
            localtelemetry.addData("LeadScrew Power:", leadScrewMotor.getPower());
            localtelemetry.addData("LeadScrew Target Pos:", leadScrewMotor.getTargetPosition());
            localtelemetry.update();
        }
        resetMotorsAndEncoders();
    }
    public void leadScrewDown(double distance, double power, double timeout) {
        resetMotorsAndEncoders();
        int tolerance = 50;
        int leadScrewPitch = 2;
        int counts = (int) Math.round(distance/leadScrewPitch * ANDYMARK_TICKS_PER_REV);
//must set direction first
        leadScrewMotor.setDirection(DcMotor.Direction.REVERSE);
//then set position
        leadScrewMotor.setTargetPosition(-counts);
//then set the mode
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
        runtime.reset();
        leadScrewMotor.setPower(power);

        while (Math.abs(leadScrewMotor.getTargetPosition() - leadScrewMotor.getCurrentPosition())
                > tolerance ) {
            if (runtime.seconds() > timeout) {
                break;
            }

            localtelemetry.addData("Current LeadScrew Counts:", (leadScrewMotor.getCurrentPosition()));
            localtelemetry.addData("LeadScrew Power:", leadScrewMotor.getPower());
            localtelemetry.addData("LeadScrew Target Pos:", leadScrewMotor.getTargetPosition());
            localtelemetry.update();
        }
        resetMotorsAndEncoders();
    }}