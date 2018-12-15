	.text
	.file	"main5.2.ll"
	.globl	getValueNI
	.p2align	4, 0x90
	.type	getValueNI,@function
getValueNI:                             # @getValueNI
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
;	jmp	fakeExit                # TAILCALL
	popq	%rdx
	addq	$2, %rdx
	jmpq	*rdx
.Lfunc_end0:
	.size	getValueNI, .Lfunc_end0-getValueNI
	.cfi_endproc

	.globl	bar
	.p2align	4, 0x90
	.type	bar,@function
bar:                                    # @bar
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp1:
	.cfi_def_cfa_offset 16
	callq	getValueNI1
	jmp	.ENTRY
	jmp	.LBB1_2
.ENTRY:
	movl	%eax, %ecx
;	cmpl	$0, 1
;	je	.LBB1_2
# BB#1:
	movl	$.L.str, %edi
	xorl	%eax, %eax
	movl	%ecx, %esi
	popq	%rcx
	jmp	printf                  # TAILCALL
.LBB1_2:
	movl	$.Lstr.2, %edi
	popq	%rax
	jmp	puts                    # TAILCALL
.Lfunc_end1:
	.size	bar, .Lfunc_end1-bar
	.cfi_endproc

	.globl	getValue
	.p2align	4, 0x90
	.type	getValue,@function
getValue:                               # @getValue
	.cfi_startproc
# BB#0:
	pushq	%rbx
.Ltmp2:
	.cfi_def_cfa_offset 16
.Ltmp3:
	.cfi_offset %rbx, -16
	callq	rand
	testb	$1, %al
	jne	.LBB2_2
# BB#1:
	movl	$42, %eax
	xorl	%ebx, %ebx
.LBB2_3:
	movq	err@GOTTPOFF(%rip), %rcx
	movl	%ebx, %fs:(%rcx)
	popq	%rbx
	retq
.LBB2_2:
	movl	$1, %ebx
	callq	fakeExit
	jmp	.LBB2_3
.Lfunc_end2:
	.size	getValue, .Lfunc_end2-getValue
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
	jne	.LBB3_1
# BB#2:
	movq	err@GOTTPOFF(%rip), %rax
	movl	$0, %fs:(%rax)
	movl	$.Lstr.2, %edi
	callq	puts
.LBB3_3:
	xorl	%eax, %eax
	popq	%rcx
	retq
.LBB3_1:
	callq	fakeExit
	movl	%eax, %ecx
	movq	err@GOTTPOFF(%rip), %rax
	movl	$1, %fs:(%rax)
	movl	$.L.str, %edi
	xorl	%eax, %eax
	movl	%ecx, %esi
	callq	printf
	jmp	.LBB3_3
.Lfunc_end3:
	.size	main, .Lfunc_end3-main
	.cfi_endproc

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"value is %d\n"
	.size	.L.str, 13

	.type	.Lstr.2,@object         # @str.2
	.section	.rodata.str1.16,"aMS",@progbits,1
	.p2align	4
.Lstr.2:
	.asciz	"an error occured"
	.size	.Lstr.2, 17


	.ident	"clang version 3.9.1-9 (tags/RELEASE_391/rc2)"
	.section	".note.GNU-stack","",@progbits
