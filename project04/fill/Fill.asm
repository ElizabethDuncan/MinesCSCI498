//This program fills the screen black when a key is pressed

	@8191
	D=A
	@avail
	M=D
(LOOP)
	@fill
	M=-1
	D=0
	@KBD
	D=M
	@lala
	D;JNE
	@fill
	M=0
(lala)
	@SCREEN
	D=A
	@avail
	M=M-1
	D=M+D
	@watever
	M=D
	@fill
	D=M
	@watever
	A=M
	M=D
	
	@avail
	D=M
	@LOOP
	D;JNE
	@8191
	D=A
	@avail
	M=D	
	
	@LOOP
	0;JMP
(END)
	@END
	0;JMP
