	.text
	.file	"main2.c"
	.globl	fakeExit2
	.p2align	4, 0x90
	.type	fakeExit2,@function
fakeExit2:                              # @fakeExit2
	.cfi_startproc
# BB#0:
	xorps	%xmm0, %xmm0
	movups	%xmm0, 80(%rdi)
	movups	%xmm0, 64(%rdi)
	movups	%xmm0, 48(%rdi)
	movups	%xmm0, 32(%rdi)
	movups	%xmm0, 16(%rdi)
	movups	%xmm0, (%rdi)
	movl	$0, 96(%rdi)
	movl	$1, 100(%rdi)
	movq	%rdi, %rax
	retq
.Lfunc_end0:
	.size	fakeExit2, .Lfunc_end0-fakeExit2
	.cfi_endproc

	.globl	getValue
	.p2align	4, 0x90
	.type	getValue,@function
getValue:                               # @getValue
	.cfi_startproc
# BB#0:
	pushq	%rbx
.Ltmp0:
	.cfi_def_cfa_offset 16
.Ltmp1:
	.cfi_offset %rbx, -16
	movq	%rdi, %rbx
	callq	rand
	testb	$1, %al
	jne	.LBB1_1
# BB#3:
	movups	.L.str+80(%rip), %xmm0
	movups	%xmm0, 80(%rbx)
	movups	.L.str+64(%rip), %xmm0
	movups	%xmm0, 64(%rbx)
	movups	.L.str+48(%rip), %xmm0
	movups	%xmm0, 48(%rbx)
	movups	.L.str+32(%rip), %xmm0
	movups	%xmm0, 32(%rbx)
	movups	.L.str+16(%rip), %xmm0
	movups	%xmm0, 16(%rbx)
	movups	.L.str(%rip), %xmm0
	movups	%xmm0, (%rbx)
	movl	$0, 96(%rbx)
	movl	$0, 100(%rbx)
.LBB1_2:
	movq	%rbx, %rax
	popq	%rbx
	retq
.LBB1_1:
	movq	%rbx, %rdi
	callq	fakeExit2
;	jmp	.LBB1_2
	movq	%rbx, %rax
	popq	%rbx
;	retq
	popq	%rdx
	addq	$2, %rdx
	jmpq	*%rdx

.Lfunc_end1:
	.size	getValue, .Lfunc_end1-getValue
	.cfi_endproc

	.globl	main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# BB#0:
	subq	$104, %rsp
.Ltmp2:
	.cfi_def_cfa_offset 112
	xorl	%edi, %edi
	callq	time
	movl	%eax, %edi
	callq	srand
	leaq	(%rsp), %rdi
	callq	getValue
	jmp	.ENTRY
	jmp	.LBB2_1
.ENTRY:
	movl	100(%rsp), %esi
	testl	%esi, %esi
;	je	.LBB2_1
# BB#2:
	movl	$.L.str.2, %edi
	xorl	%eax, %eax
	callq	printf
	jmp	.LBB2_3
.LBB2_1:
	leaq	(%rsp), %rsi
	movl	$.L.str.1, %edi
	xorl	%eax, %eax
	callq	printf
.LBB2_3:
	xorl	%eax, %eax
	addq	$104, %rsp
	retq
.Lfunc_end2:
	.size	main, .Lfunc_end2-main
	.cfi_endproc

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.4,"aMS",@progbits,1
	.p2align	2
.L.str:
	.asciz	"aaaa"
	.size	.L.str, 5

	.type	.L.str.1,@object        # @.str.1
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str.1:
	.asciz	"value is %s\n"
	.size	.L.str.1, 13

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"error is %d\n"
	.size	.L.str.2, 13


	.ident	"clang version 3.9.1-9 (tags/RELEASE_391/rc2)"
	.section	".note.GNU-stack","",@progbits
