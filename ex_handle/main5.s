	.text
	.file	"main5.c"
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
	andl	$1, %eax
	movq	err@GOTTPOFF(%rip), %rcx
	movl	%eax, %fs:(%rcx)
	movl	$42, %eax
	popq	%rcx
	retq
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
	andl	$1, %eax
	movq	err@GOTTPOFF(%rip), %rcx
	movl	%eax, %fs:(%rcx)
	je	.LBB1_2
# BB#1:
	movl	$.L.str, %edi
	movl	$42, %esi
	xorl	%eax, %eax
	callq	printf
.LBB1_3:
	xorl	%eax, %eax
	popq	%rcx
	retq
.LBB1_2:
	movl	$.Lstr, %edi
	callq	puts
	jmp	.LBB1_3
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
