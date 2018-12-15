	.text
	.file	"main.c"
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
	jne	.LBB0_1
.LBB0_2:
	movl	$42, %eax
	popq	%rcx
	retq
.LBB0_1:
	movb	$1, %fs:err@TPOFF
	jmp	.LBB0_2
.Lfunc_end0:
	.size	getValue, .Lfunc_end0-getValue
	.cfi_endproc

	.globl	main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp1:
	.cfi_def_cfa_offset 16
	xorl	%edi, %edi
	callq	time
	movl	%eax, %edi
	callq	srand
	callq	rand
	testb	$1, %al
	jne	.LBB1_1
# BB#3:
	movb	%fs:err@TPOFF, %al
	testb	%al, %al
	jne	.LBB1_2
# BB#4:
	movl	$.L.str, %edi
	movl	$42, %esi
.LBB1_5:
	xorl	%eax, %eax
	callq	printf
	xorl	%eax, %eax
	popq	%rcx
	retq
.LBB1_1:
	movb	$1, %fs:err@TPOFF
.LBB1_2:
	movl	$.L.str.1, %edi
	movl	$1, %esi
	jmp	.LBB1_5
.Lfunc_end1:
	.size	main, .Lfunc_end1-main
	.cfi_endproc

	.type	err,@object             # @err
	.section	.tbss,"awT",@nobits
	.p2align	2
err:
	.byte	0                       # 0x0
	.size	err, 1

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"value is %d\n"
	.size	.L.str, 13

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"error is %d\n"
	.size	.L.str.1, 13


	.ident	"clang version 3.9.1-9 (tags/RELEASE_391/rc2)"
	.section	".note.GNU-stack","",@progbits
