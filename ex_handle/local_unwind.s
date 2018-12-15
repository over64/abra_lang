	.text
	.file	"local_unwind.c"
	.globl	bar
	.p2align	4, 0x90
	.type	bar,@function
bar:                                    # @bar
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp0:
	.cfi_def_cfa_offset 16
	movl	$.L.str, %edi
	xorl	%eax, %eax
	callq	printf
	callq	rand
	cmpl	$32, %eax
	jne	.LBB0_2
# BB#1:
	movl	$11, %eax
	popq	%rcx
	retq
.LBB0_2:
	popq	%rax
	jmp	eh_exit_int             # TAILCALL
.Lfunc_end0:
	.size	bar, .Lfunc_end0-bar
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
	movl	$.L.str, %edi
	xorl	%eax, %eax
	callq	printf
	callq	rand
	movl	$11, %esi
	cmpl	$32, %eax
	je	.LBB1_2
# BB#1:
	callq	eh_exit_int_local()
	movl	%eax, %esi
.LBB1_2:
	movl	$.Ltmp3, %ebx
.Ltmp3:                                 # Block address taken
.LBB1_3:                                # =>This Inner Loop Header: Depth=1
	movl	$.L.str.1, %edi
	xorl	%eax, %eax
	callq	printf
	movl	$.L.str, %edi
	xorl	%eax, %eax
	callq	printf
	callq	rand
	cmpl	$32, %eax
	je	.LBB1_5
# BB#4:                                 #   in Loop: Header=BB1_3 Depth=1
	callq	eh_exit_int
.Ltmp4:                                 # Block address taken
.LBB1_5:                                #   in Loop: Header=BB1_3 Depth=1
	cmpl	$0, 291
	je	.LBB1_8
# BB#6:                                 #   in Loop: Header=BB1_3 Depth=1
	callq	eh_raised_address
	movl	$42, %esi
	cmpq	%rbx, %rax
	je	.LBB1_3
# BB#7:
	movl	$.Ltmp4, %ecx
	cmpq	%rcx, %rax
	je	.LBB1_9
.LBB1_8:
	xorl	%eax, %eax
	popq	%rbx
	retq
.LBB1_9:
	movl	$.L.str.2, %edi
	xorl	%eax, %eax
	callq	printf
	popq	%rbx
	jmp	eh_exit_int             # TAILCALL
.Lfunc_end1:
	.size	main, .Lfunc_end1-main
	.cfi_endproc

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"hello"
	.size	.L.str, 6

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"first bar ret: %d\n"
	.size	.L.str.1, 19

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"eh for bar call 2"
	.size	.L.str.2, 18


	.ident	"clang version 3.9.1-9 (tags/RELEASE_391/rc2)"
	.section	".note.GNU-stack","",@progbits
