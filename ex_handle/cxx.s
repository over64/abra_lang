	.text
	.file	"cxx.cpp"
	.globl	_Z3barv
	.p2align	4, 0x90
	.type	_Z3barv,@function
_Z3barv:                                # @_Z3barv
	.cfi_startproc
# BB#0:
	pushq	%rax
.Ltmp0:
	.cfi_def_cfa_offset 16
	callq	rand
	testl	%eax, %eax
	je	.LBB0_2
# BB#1:
	movl	$42, %eax
	popq	%rcx
	retq
.LBB0_2:
	movl	$4, %edi
	callq	__cxa_allocate_exception
	movl	$1, (%rax)
	movl	$_ZTIi, %esi
	xorl	%edx, %edx
	movq	%rax, %rdi
	callq	__cxa_throw
.Lfunc_end0:
	.size	_Z3barv, .Lfunc_end0-_Z3barv
	.cfi_endproc

	.globl	_Z3bazv
	.p2align	4, 0x90
	.type	_Z3bazv,@function
_Z3bazv:                                # @_Z3bazv
.Lfunc_begin0:
	.cfi_startproc
	.cfi_personality 3, __gxx_personality_v0
	.cfi_lsda 3, .Lexception0
# BB#0:
	pushq	%rbx
.Ltmp4:
	.cfi_def_cfa_offset 16
.Ltmp5:
	.cfi_offset %rbx, -16
	movl	$42, %ebx
.Ltmp1:
	callq	_Z3barv
.Ltmp2:
.LBB1_2:
	movl	%ebx, %eax
	popq	%rbx
	retq
.LBB1_1:
.Ltmp3:
	movq	%rax, %rdi
	callq	__cxa_begin_catch
	movl	(%rax), %ebx
	callq	__cxa_end_catch
	jmp	.LBB1_2
.Lfunc_end1:
	.size	_Z3bazv, .Lfunc_end1-_Z3bazv
	.cfi_endproc
	.section	.gcc_except_table,"a",@progbits
	.p2align	2
GCC_except_table1:
.Lexception0:
	.byte	255                     # @LPStart Encoding = omit
	.byte	3                       # @TType Encoding = udata4
	.asciz	"\242\200\200"          # @TType base offset
	.byte	3                       # Call site Encoding = udata4
	.byte	26                      # Call site table length
	.long	.Ltmp1-.Lfunc_begin0    # >> Call Site 1 <<
	.long	.Ltmp2-.Ltmp1           #   Call between .Ltmp1 and .Ltmp2
	.long	.Ltmp3-.Lfunc_begin0    #     jumps to .Ltmp3
	.byte	1                       #   On action: 1
	.long	.Ltmp2-.Lfunc_begin0    # >> Call Site 2 <<
	.long	.Lfunc_end1-.Ltmp2      #   Call between .Ltmp2 and .Lfunc_end1
	.long	0                       #     has no landing pad
	.byte	0                       #   On action: cleanup
	.byte	1                       # >> Action Record 1 <<
                                        #   Catch TypeInfo 1
	.byte	0                       #   No further actions
                                        # >> Catch TypeInfos <<
	.long	_ZTIi                   # TypeInfo 1
	.p2align	2

	.text
	.globl	main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# BB#0:
	jmp	_Z3bazv                 # TAILCALL
.Lfunc_end2:
	.size	main, .Lfunc_end2-main
	.cfi_endproc


	.ident	"clang version 3.9.1-9 (tags/RELEASE_391/rc2)"
	.section	".note.GNU-stack","",@progbits
