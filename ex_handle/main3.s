	.text
	.file	"main3.c"
	.globl	getValue
	.p2align	4, 0x90
	.type	getValue,@function
getValue:                               # @getValue
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp0:
	.cfi_def_cfa_offset 16
	callq	rand
	testb	$1, %al
	jne	.LBB0_2
# BB#1:
	movl	$42, %eax
	popq	%rcx
	retq
.LBB0_2:
	popq	%rax
	jmp	fakeExit                # TAILCALL
.Lfunc_end0:
	.size	getValue, .Lfunc_end0-getValue
	.cfi_endproc

	.globl	main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# BB#0:
	pushq	%rbx
.Ltmp1:
	.cfi_def_cfa_offset 16
.Ltmp2:
	.cfi_offset %rbx, -16
	xorl	%edi, %edi
	callq	time
	movl	%eax, %edi
	callq	srand
	callq	rand
	movl	$42, %ebx
	testb	$1, %al
	jne	.LBB1_1
.LBB1_2:
	callq	__errno_location
	cmpl	$1, (%rax)
	jne	.LBB1_4
# BB#3:
	movl	$.L.str, %edi
	xorl	%eax, %eax
	movl	%ebx, %esi
	callq	printf
	jmp	.LBB1_5
.LBB1_4:
	movl	$.Lstr, %edi
	callq	puts
.LBB1_5:
	xorl	%eax, %eax
	popq	%rbx
	retq
.LBB1_1:
	callq	fakeExit
	movl	%eax, %ebx
	jmp	.LBB1_2
.Lfunc_end1:
	.size	main, .Lfunc_end1-main
	.cfi_endproc

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"value is %d\n"
	.size	.L.str, 13

	.type	.Lstr,@object           # @str
	.section	.rodata.str1.16,"aMS",@progbits,1
	.p2align	4
.Lstr:
	.asciz	"an error occured"
	.size	.Lstr, 17


	.ident	"clang version 3.9.1-9 (tags/RELEASE_391/rc2)"
	.section	".note.GNU-stack","",@progbits
