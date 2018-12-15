	.text
	.file	"main4.c"
	.globl	set_err_zero
	.p2align	4, 0x90
	.type	set_err_zero,@function
set_err_zero:                           # @set_err_zero
	.cfi_startproc
# BB#0:
	movq	err@GOTTPOFF(%rip), %rax
	movl	$0, %fs:(%rax)
	retq
.Lfunc_end0:
	.size	set_err_zero, .Lfunc_end0-set_err_zero
	.cfi_endproc

	.globl	set_err_one
	.p2align	4, 0x90
	.type	set_err_one,@function
set_err_one:                            # @set_err_one
	.cfi_startproc
# BB#0:
	movq	err@GOTTPOFF(%rip), %rax
	movl	$1, %fs:(%rax)
	retq
.Lfunc_end1:
	.size	set_err_one, .Lfunc_end1-set_err_one
	.cfi_endproc

	.globl	get_errno
	.p2align	4, 0x90
	.type	get_errno,@function
get_errno:                              # @get_errno
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp0:
	.cfi_def_cfa_offset 16
	callq	__errno_location
	movl	(%rax), %eax
	popq	%rcx
	retq
.Lfunc_end2:
	.size	get_errno, .Lfunc_end2-get_errno
	.cfi_endproc

	.globl	getValueNI
	.p2align	4, 0x90
	.type	getValueNI,@function
getValueNI:                             # @getValueNI
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp1:
	.cfi_def_cfa_offset 16
	callq	rand
	testb	$1, %al
	jne	.LBB3_2
# BB#1:
	movl	$42, %eax
	popq	%rcx
	retq
.LBB3_2:
	popq	%rax
	jmp	fakeExit                # TAILCALL
.Lfunc_end3:
	.size	getValueNI, .Lfunc_end3-getValueNI
	.cfi_endproc

	.globl	getValue2
	.p2align	4, 0x90
	.type	getValue2,@function
getValue2:                              # @getValue2
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp2:
	.cfi_def_cfa_offset 16
	callq	rand
	testb	$1, %al
	jne	.LBB4_2
# BB#1:
	callq	__errno_location
	movl	$0, (%rax)
	movl	$42, %eax
	popq	%rcx
	retq
.LBB4_2:
	popq	%rax
	jmp	fakeExit                # TAILCALL
.Lfunc_end4:
	.size	getValue2, .Lfunc_end4-getValue2
	.cfi_endproc

	.globl	getValue
	.p2align	4, 0x90
	.type	getValue,@function
getValue:                               # @getValue
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp3:
	.cfi_def_cfa_offset 16
	callq	rand
	testb	$1, %al
	jne	.LBB5_2
# BB#1:
	callq	set_err_zero
	movl	$42, %eax
	popq	%rcx
	retq
.LBB5_2:
	callq	set_err_one
	popq	%rax
	jmp	fakeExit                # TAILCALL
.Lfunc_end5:
	.size	getValue, .Lfunc_end5-getValue
	.cfi_endproc

	.globl	main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp4:
	.cfi_def_cfa_offset 16
	xorl	%edi, %edi
	callq	time
	movl	%eax, %edi
	callq	srand
	callq	rand
	testb	$1, %al
	jne	.LBB6_2
# BB#1:
	callq	set_err_zero
	movl	$42, %esi
.LBB6_3:
	movq	err@GOTTPOFF(%rip), %rax
	cmpl	$0, %fs:(%rax)
	je	.LBB6_5
# BB#4:
	movl	$.L.str, %edi
	xorl	%eax, %eax
	callq	printf
.LBB6_6:
	xorl	%eax, %eax
	popq	%rcx
	retq
.LBB6_2:
	callq	set_err_one
	callq	fakeExit
	movl	%eax, %esi
	jmp	.LBB6_3
.LBB6_5:
	movl	$.Lstr, %edi
	callq	puts
	jmp	.LBB6_6
.Lfunc_end6:
	.size	main, .Lfunc_end6-main
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
