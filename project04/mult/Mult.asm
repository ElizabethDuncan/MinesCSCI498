
//This program multiplies two numbers :)

	@R2
	M=0
	@total	
	M=0  //total=0
(LOOP)

	@R0 
	D=M
	@END
	D;JEQ //if r1 > 0 goto END
	@R0
	D=M
	D=D-1  //r0=r0-1
	M=D
	@R1
	D=M
	@total
	M=D+M
	D=M
	@R2
	M=D
	@LOOP
	0;JMP //jump to loop
(END)
	@END
	0;JMP
	
	